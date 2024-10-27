package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingOrderDTO;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.Resources;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.entity.orders.BuildingOrder;
import org.retal.offgame.repository.BuildingOrderRepository;
import org.retal.offgame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.retal.offgame.entity.orders.OrderStatus.created;
import static org.retal.offgame.entity.orders.OrderStatus.started;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingOrderServiceImpl extends AbstractCrudService<BuildingOrder, Long> implements BuildingOrderService {

    private final BuildingOrderRepository buildingOrderRepository;
    private final BuildingInstanceService buildingInstanceService;
    private final ResourcesService resourcesService;
    private final PlanetService planetService;

    private final static double REFUND_MULTIPLIER = 0.9;

    @Override
    public Collection<BuildingOrder> getUnprocessedOrders() {
        return buildingOrderRepository.findUnprocessedFinishedOrders();
    }

    @Override
    public void initCreatedOrders() {
        Collection<BuildingOrder> orderCandidates = getBuildingOrdersToInit();
        Map<Long, Map<Class<? extends Upgradeable>, Long>> specialEntityLevelsByPlanet = orderCandidates.stream()
                .map(BuildingOrder::getBuildingInstance)
                .map(BuildingInstance::getPlanet)
                .map(Planet::getId)
                .distinct()
                .collect(toMap(
                        Function.identity(),
                        planetService::getSpecialEntityLevels
                ));

        List<BuildingOrder> undoableOrders = new ArrayList<>();

        Map<BuildingOrder, Resources> ordersToInit = orderCandidates.stream()
                .map(order -> {
                    BuildingInstance buildingInstance = order.getBuildingInstance();
                    Long planetId = buildingInstance.getPlanet().getId();
                    long levelDiff = Math.abs(order.getOrderValue() - buildingInstance.getLevel());
                    if (canStartOrder(order, specialEntityLevelsByPlanet.get(planetId)) && levelDiff == 1) {
                        return order;
                    }

                    undoableOrders.add(order);
                    return null;
                })
                .filter(Objects::nonNull)
                .map(this::processOrder)
                .collect(toMap(
                        Function.identity(),
                        this::subtractResources
                ));

        deleteAll(undoableOrders);
        saveAll(ordersToInit.keySet());
        resourcesService.saveAll(ordersToInit.values());
    }

    private BuildingOrder processOrder(BuildingOrder order) {
        Instant finishedAt = Instant.now().plus(order.getDuration(), ChronoUnit.SECONDS);
        order.setStatus(started);
        order.setFinishedAt(finishedAt);

        return order;
    }

    private Resources subtractResources(BuildingOrder buildingOrder) {
        BuildingInstance buildingInstance = buildingOrder.getBuildingInstance();

        ResourcesDTO cost = getCost(buildingOrder);
        Resources resources = buildingInstance.getPlanet().getResources();
        resources.updateResources(cost.negate());

        return resources;
    }

    private ResourcesDTO getCost(BuildingOrder buildingOrder) {
        boolean isUpgrade = buildingOrder.isUpgrade();
        BuildingInstance buildingInstance = buildingOrder.getBuildingInstance();
        Building building = buildingInstance.getBuilding();
        Function<Long, ResourcesDTO> resourceFunction = isUpgrade ? building::calculateBuildingCost : building::calculateDemolishCost;
        Long level = isUpgrade ? buildingOrder.getOrderValue() : buildingInstance.getLevel();
        return resourceFunction.apply(level);
    }

    private Collection<BuildingOrder> getBuildingOrdersToInit() {
        return buildingOrderRepository.getBuildingCandidatesToInit();
    }

    @Override
    public BuildingOrderInfo createBuildingOrder(BuildingOrderDTO dto) {
        Long planetId = dto.getPlanetId();
        boolean isUpgrade = dto.getIsUpgrade();
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planetId);

        return buildingInstanceService.findByPlanetIdAndBuildingId(planetId, dto.getBuildingId())
                .map(buildingInstance -> toBuildingOrder(buildingInstance, isUpgrade, specialEntityLevels))
                .filter(buildingOrder -> canStartOrder(buildingOrder, specialEntityLevels))
                .map(this::createOrder)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    //TODO check for requirements
    private boolean canStartOrder(BuildingOrder buildingOrder, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {

        BuildingInstance buildingInstance = buildingOrder.getBuildingInstance();
        ResourcesDTO cost = getCost(buildingOrder);
        Planet planet = buildingInstance.getPlanet();
        ResourcesDTO currentResources = planet.getResources().toDTO();
        long totalFields = planet.getTotalFields(specialEntityLevels);
        long usedFields = planet.getUsedFields(specialEntityLevels);

        return currentResources.isMoreOrEqualThan(cost)
                && (!buildingOrder.isUpgrade() || (usedFields < totalFields))
                && buildingOrder.getOrderValue() >= 0;
    }

    private BuildingOrder toBuildingOrder(BuildingInstance buildingInstance, boolean isUpgrade, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        Long level = buildingInstance.getLevel();
        int diff = isUpgrade ? 1 : -1;
        Long lastOrderedLevel = buildingOrderRepository.findLatestActiveOrderForBuildingInstance(buildingInstance)
                .map(BuildingOrder::getOrderValue)
                .orElse(level) + diff;

        long buildingTime = buildingInstance.getBuilding().calculateBuildingTime(lastOrderedLevel, specialBuildingLevels).longValue();
        Instant createdAt = Instant.now();

        return BuildingOrder.builder()
                .buildingInstance(buildingInstance)
                .orderValue(lastOrderedLevel)
                .createdAt(createdAt)
                .duration(buildingTime)
                .status(created)
                .build();
    }

    private BuildingOrderInfo createOrder(BuildingOrder buildingOrder) {
        buildingOrderRepository.save(buildingOrder);
        initCreatedOrders();
        return toBuildingOrderInfo(buildingOrder);
    }

    @Override
    public List<BuildingOrderInfo> getPlanetBuildingOrders(Long planetId) {
        return buildingOrderRepository.findBuildingOrdersByBuildingInstancePlanetIdAndStatusInOrderByCreatedAt(planetId, Set.of(created, started)).stream()
                .map(this::toBuildingOrderInfo)
                .collect(toList());
    }

    private BuildingOrderInfo toBuildingOrderInfo(BuildingOrder buildingOrder) {
        BuildingInstance buildingInstance = buildingOrder.getBuildingInstance();
        return BuildingOrderInfo.builder()
                .id(buildingOrder.getId())
                .endTime(buildingOrder.getFinishedAt())
                .name(buildingInstance.getBuilding().getName())
                .value(buildingOrder.getOrderValue())
                .isUpgrade(buildingOrder.isUpgrade())
                .build();
    }

    @Override
    public void cancelBuildingOrder(Long orderId) {
        //todo move notfoundexception supplier to constant for all?
        BuildingOrder buildingOrder = buildingOrderRepository.findById(orderId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if (buildingOrder.getStatus() == started) {
            BuildingInstance buildingInstance = buildingOrder.getBuildingInstance();
            ResourcesDTO refund = getCost(buildingOrder).multiplyBy(REFUND_MULTIPLIER);
            Resources resources = buildingInstance.getPlanet().getResources();
            resources.updateResources(refund);
            resourcesService.saveOrUpdate(resources);
        }

        deleteById(orderId);
    }

    @Override
    protected CrudRepository<BuildingOrder, Long> getRepository() {
        return buildingOrderRepository;
    }
}
