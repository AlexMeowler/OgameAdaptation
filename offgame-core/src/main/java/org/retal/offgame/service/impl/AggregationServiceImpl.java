package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.dto.PlanetOverview;
import org.retal.offgame.dto.UnitOrderInfo;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AggregationServiceImpl implements AggregationService {

    private final PlanetService planetService;
    private final TechnologyOrderService technologyOrderService;
    private final UnitOrderService unitOrderService;
    private final BuildingOrderService buildingOrderService;


    @Override
    @Transactional
    public PlanetOverview getPlanetOverview(Long planetId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        return toPlanetOverview(planet);
    }

    private PlanetOverview toPlanetOverview(Planet planet) {
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planet.getId());
        List<UnitOrderInfo> unitOrders = unitOrderService.getPlanetUnitOrders(planet.getId());
        Long spaceYardTotalTime = !unitOrders.isEmpty() ? getUnitOrdersTotalBuildTime(unitOrders) : null;
        BuildingOrderInfo activeBuildingOrder = buildingOrderService.getPlanetBuildingOrders(planet.getId()).stream()
                .filter(boi -> Objects.nonNull(boi.getEndTime()))
                .findFirst()
                .orElse(null);

        return PlanetOverview.builder()
                .serverTime(Instant.now())
                .name(planet.getName())
                .imageName(planet.getImageName())
                .diameter(planet.getDiameter())
                .activeTechnologyOrder(technologyOrderService.getTechnologyOrder(planet.getId()))
                .activeBuildingOrder(activeBuildingOrder)
                .totalFields(planet.getTotalFields(specialEntityLevels))
                .usedFields(planet.getUsedFields(specialEntityLevels))
                .spaceYardTotalTimeLeft(spaceYardTotalTime)
                .minTemperature(planet.getMinTemperature())
                .maxTemperature(planet.getMaxTemperature())
                .galaxy(planet.getGalaxy())
                .system(planet.getSystem())
                .position(planet.getPosition())
                .build();
    }

    private long getUnitOrdersTotalBuildTime(List<UnitOrderInfo> unitOrders) {
            UnitOrderInfo currentOrder = unitOrders.get(0);
        long buildTime = Duration.between(Instant.now(), currentOrder.getCurrentUnitEndTime()).getSeconds()
                + (currentOrder.getAmountLeft() - 1) * currentOrder.getSingleUnitDuration();

        for (int i = 1; i < unitOrders.size(); i++) {
            UnitOrderInfo order = unitOrders.get(i);
            buildTime += order.getAmountLeft() * order.getSingleUnitDuration();
        }

        return buildTime;
    }
}
