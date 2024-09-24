package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingOrderDTO;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.BuildingOrder;
import org.retal.offgame.entity.Resources;
import org.retal.offgame.repository.BuildingInstanceRepository;
import org.retal.offgame.repository.BuildingOrderRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.BuildingOrderService;
import org.retal.offgame.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.retal.offgame.entity.BuildingOrder.Status.created;
import static org.retal.offgame.entity.BuildingOrder.Status.started;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingOrderServiceImpl extends AbstractCrudService<BuildingOrder, Long> implements BuildingOrderService {

    private final BuildingOrderRepository buildingOrderRepository;
    private final BuildingInstanceRepository buildingInstanceRepository;
    private final ResourcesService resourcesService;

    @Override
    public Collection<BuildingOrder> getUnprocessedOrders() {
        return buildingOrderRepository.findUnprocessedFinishedOrders();
    }

    @Override
    public void initCreatedOrders() {
        Map<BuildingOrder, Resources> ordersToInit = getBuildingOrdersToInit().stream()
                .filter(order -> canStartOrder(order.getBuildingInstance()))
                .map(this::processOrder)
                .collect(toMap(
                        Function.identity(),
                        this::subtractResources
                ));

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
        Long level = buildingInstance.getLevel() + 1;
        ResourcesDTO cost = buildingInstance.getBuilding().calculateBuildingCost(level);

        Resources resources = buildingInstance.getPlanet().getResources();
        resources.updateResources(cost.negate());

        return resources;
    }

    private Collection<BuildingOrder> getBuildingOrdersToInit() {
        return buildingOrderRepository.getBuildingCandidatesToInit();
    }

    @Override
    public BuildingOrderInfo createBuildingOrder(BuildingOrderDTO dto) {
        //todo validation and subtract resources
        return buildingInstanceRepository.findByPlanetIdAndBuildingId(dto.getPlanetId(), dto.getBuildingId())
                .filter(this::canStartOrder)
                .map(this::toBuildingOrder)
                .map(this::createOrder)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private boolean canStartOrder(BuildingInstance buildingInstance) {
        Long level = buildingInstance.getLevel() + 1;

        ResourcesDTO cost = buildingInstance.getBuilding().calculateBuildingCost(level);
        ResourcesDTO currentResources = buildingInstance.getPlanet().getResources().toDTO();

        return currentResources.isMoreOrEqualThan(cost);
    }

    private BuildingOrder toBuildingOrder(BuildingInstance buildingInstance) {
        Long level = buildingInstance.getLevel();
        Long lastOrderedLevel = buildingOrderRepository.findLatestActiveOrderForBuildingInstance(buildingInstance)
                .map(BuildingOrder::getOrderValue)
                .orElse(level) + 1;

        long buildingTime = buildingInstance.getBuilding().calculateBuildingTime(lastOrderedLevel).longValue();
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
                .build();
    }

    @Override
    protected CrudRepository<BuildingOrder, Long> getRepository() {
        return buildingOrderRepository;
    }
}
