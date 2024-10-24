package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.dto.UnitOrderDTO;
import org.retal.offgame.dto.UnitOrderInfo;
import org.retal.offgame.entity.Resources;
import org.retal.offgame.entity.UnitInstance;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.orders.UnitOrder;
import org.retal.offgame.repository.UnitOrderRepository;
import org.retal.offgame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.retal.offgame.entity.orders.OrderStatus.created;
import static org.retal.offgame.entity.orders.OrderStatus.started;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UnitOrderServiceImpl extends AbstractCrudService<UnitOrder, Long> implements UnitOrderService {

    private final UnitOrderRepository unitOrderRepository;
    private final UnitInstanceService unitInstanceService;
    private final ResourcesService resourcesService;
    private final PlanetService planetService;

    private final static double REFUND_MULTIPLIER = 0.5;

    @Override
    public Collection<UnitOrder> getUnprocessedOrders() {
        return unitOrderRepository.findUnprocessedFinishedOrders();
    }

    @Override
    public void initCreatedOrders() {
        //todo validation when trying to subtract
        List<UnitOrder> ordersToInit = unitOrderRepository.getUnitCandidatesToInit().stream()
                .map(this::processOrder)
                .collect(toList());

        saveAll(ordersToInit);
    }

    private UnitOrder processOrder(UnitOrder order) {
        Instant finishedAt = Instant.now().plus(order.getSingleUnitDuration(), ChronoUnit.SECONDS);
        order.setStatus(started);
        order.setUnitFinishedAt(finishedAt);

        return order;
    }

    @Override
    public UnitOrderInfo createUnitOrder(UnitOrderDTO dto) {
        Long planetId = dto.getPlanetId();
        Long amount = dto.getAmount();
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planetId);

        return unitInstanceService.findByPlanetIdAndUnitId(planetId, dto.getUnitId())
                .map(unitInstance -> toUnitOrder(unitInstance, amount, specialEntityLevels))
                .filter(unitOrder -> unitOrder.getAmountLeft() > 0)
                .map(this::createOrder)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private UnitOrder toUnitOrder(UnitInstance unitInstance, Long amount, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {

        long buildingTime = unitInstance.getUnit().calculateBuildingTime(specialBuildingLevels).longValue();
        Instant createdAt = Instant.now();

        ResourcesDTO cost = unitInstance.getUnit().getBuildingCost();
        ResourcesDTO currentResources = unitInstance.getPlanet().getResources().toDTO();

        long realAmount = Math.min(amount, calculateMaxPossibleAmount(cost, currentResources));

        return UnitOrder.builder()
                .unitInstance(unitInstance)
                .amountLeft(realAmount)
                .createdAt(createdAt)
                .singleUnitDuration(buildingTime)
                .status(created)
                .build();
    }

    private long calculateMaxPossibleAmount(ResourcesDTO cost, ResourcesDTO currentResources) {
        return Stream.<Function<ResourcesDTO, ResourceDTO>>of(ResourcesDTO::getMetal, ResourcesDTO::getCrystal, ResourcesDTO::getDeuterium, ResourcesDTO::getEnergy)
                .map(func -> Pair.of(func.apply(currentResources).amount(), func.apply(cost).amount()))
                .filter(pair -> pair.getSecond() != 0)
                .map(pair -> (long) (pair.getFirst() / pair.getSecond()))
                .min(Comparator.comparingLong(a -> a))
                .orElse(0L);
    }

    private UnitOrderInfo createOrder(UnitOrder unitOrder) {
        subtractResources(unitOrder);
        unitOrderRepository.save(unitOrder);
        initCreatedOrders();
        return toUnitOrderInfo(unitOrder);
    }

    private void subtractResources(UnitOrder unitOrder) {
        UnitInstance unitInstance = unitOrder.getUnitInstance();

        ResourcesDTO cost = getOrderCost(unitOrder);
        Resources resources = unitInstance.getPlanet().getResources();
        resources.updateResources(cost.negate());

        resourcesService.saveOrUpdate(resources);
    }

    private ResourcesDTO getOrderCost(UnitOrder unitOrder) {
        Long amount = unitOrder.getAmountLeft();
        return unitOrder.getUnitInstance().getUnit().getBuildingCost().multiplyBy(amount);
    }

    @Override
    public List<UnitOrderInfo> getPlanetUnitOrders(Long planetId) {
        return unitOrderRepository.findUnitOrdersByUnitInstancePlanetIdAndStatusInOrderByCreatedAt(planetId, Set.of(created, started)).stream()
                .map(this::toUnitOrderInfo)
                .collect(toList());
    }

    private UnitOrderInfo toUnitOrderInfo(UnitOrder unitOrder) {
        UnitInstance unitInstance = unitOrder.getUnitInstance();
        return UnitOrderInfo.builder()
                .id(unitOrder.getId())
                .currentUnitEndTime(unitOrder.getUnitFinishedAt())
                .name(unitInstance.getUnit().getName())
                .amountLeft(unitOrder.getAmountLeft())
                .singleUnitDuration(unitOrder.getSingleUnitDuration())
                .build();
    }

    @Override
    public void cancelUnitOrder(Long orderId) {
        UnitOrder unitOrder = unitOrderRepository.findById(orderId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        UnitInstance unitInstance = unitOrder.getUnitInstance();
        ResourcesDTO refund = getOrderCost(unitOrder).multiplyBy(REFUND_MULTIPLIER);
        Resources resources = unitInstance.getPlanet().getResources();
        resources.updateResources(refund);
        resourcesService.saveOrUpdate(resources);

        deleteById(orderId);
    }

    @Override
    protected CrudRepository<UnitOrder, Long> getRepository() {
        return unitOrderRepository;
    }
}
