package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.dto.TechnologyOrderDTO;
import org.retal.offgame.dto.TechnologyOrderInfo;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.Resources;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.entity.TechnologyOrder;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.repository.TechnologyInstanceRepository;
import org.retal.offgame.repository.TechnologyOrderRepository;
import org.retal.offgame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TechnologyOrderServiceImpl extends AbstractCrudService<TechnologyOrder, Long> implements TechnologyOrderService {

    private final TechnologyOrderRepository technologyOrderRepository;
    private final TechnologyInstanceRepository technologyInstanceRepository;
    private final ResourcesService resourcesService;
    private final BuildingService buildingService;
    private final PlanetService planetService;

    @Override
    public Collection<TechnologyOrder> getUnprocessedOrders() {
        return technologyOrderRepository.findUnprocessedFinishedOrders();
    }

    private Resources subtractResources(TechnologyOrder technologyOrder, Planet planet) {
        TechnologyInstance technologyInstance = technologyOrder.getTechnologyInstance();
        Long level = technologyInstance.getLevel() + 1;
        ResourcesDTO cost = technologyInstance.getTechnology().calculateBuildingCost(level);

        Resources resources = planet.getResources();
        resources.updateResources(cost.negate());

        return resources;
    }

    @Override
    @Transactional
    public TechnologyOrderInfo createTechnologyOrder(TechnologyOrderDTO dto) {
        Long planetId = dto.getPlanetId();
        Map<Class<? extends Building>, Long> specialBuildingLevels = buildingService.getSpecialBuildingLevels(planetId);
        Planet planet = planetService.getPlanetInfo(planetId);
        return technologyInstanceRepository.findByOwnerIdAndTechnologyId(planet.getOwner().getId(), dto.getTechnologyId())
                .filter(technologyInstance -> canStartOrder(technologyInstance, planet))
                .map(technologyInstance -> toTechnologyOrder(technologyInstance, planet, specialBuildingLevels))
                .map(technologyOrder -> createOrder(technologyOrder, planet))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private boolean canStartOrder(TechnologyInstance technologyInstance, Planet planet) {
        Long level = technologyInstance.getLevel() + 1;

        ResourcesDTO cost = technologyInstance.getTechnology().calculateBuildingCost(level);
        ResourcesDTO currentResources = planet.getResources().toDTO();

        return currentResources.isMoreOrEqualThan(cost);
    }

    private TechnologyOrder toTechnologyOrder(TechnologyInstance technologyInstance, Planet planet, Map<Class<? extends Building>, Long> specialBuildingLevels) {
        Long level = technologyInstance.getLevel() + 1;

        long researchTime = technologyInstance.getTechnology().calculateBuildingTime(level, specialBuildingLevels).longValue();
        Instant finishedAt = Instant.now().plus(researchTime, ChronoUnit.SECONDS);

        return TechnologyOrder.builder()
                .technologyInstance(technologyInstance)
                .researchingPlanet(planet)
                .finishedAt(finishedAt)
                .build();
    }

    private TechnologyOrderInfo createOrder(TechnologyOrder technologyOrder, Planet planet) {
        technologyOrderRepository.save(technologyOrder);
        Resources resources = subtractResources(technologyOrder, planet);
        resourcesService.saveOrUpdate(resources);

        return toTechnologyOrderInfo(technologyOrder);
    }

    @Override
    @Transactional
    public TechnologyOrderInfo getTechnologyOrder(Long planetId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        return technologyOrderRepository.findTechnologyOrderByTechnologyInstanceOwnerId(planet.getOwner().getId())
                .map(this::toTechnologyOrderInfo)
                .orElse(null);
    }

    private TechnologyOrderInfo toTechnologyOrderInfo(TechnologyOrder technologyOrder) {
        TechnologyInstance technologyInstance = technologyOrder.getTechnologyInstance();
        return TechnologyOrderInfo.builder()
                .id(technologyOrder.getId())
                .technologyId(technologyInstance.getTechnology().getId())
                .planetId(technologyOrder.getResearchingPlanet().getId())
                .endTime(technologyOrder.getFinishedAt())
                .build();
    }

    @Override
    protected CrudRepository<TechnologyOrder, Long> getRepository() {
        return technologyOrderRepository;
    }
}
