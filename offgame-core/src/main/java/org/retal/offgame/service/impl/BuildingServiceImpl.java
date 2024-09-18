package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.repository.BuildingRepository;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final PlanetService planetService;

    @Override
    public List<BuildingDTO> getPlanetBuildings(Long planetId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        return buildingRepository.findByPlanetId(planetId).stream()
                .map(building -> toDTO(building, planet))
                .collect(Collectors.toList());
    }

    private BuildingDTO toDTO(Building building, Planet planet) {
        Long level = Optional.ofNullable(building)
                .map(Building::getInstances)
                .map(Set::iterator)
                .filter(Iterator::hasNext)
                .map(Iterator::next)
                .map(BuildingInstance::getLevel)
                .orElse(0L);

        ResourcesDTO buildingCost = building.calculateBuildingCost(level);
        int temperature = planet.averageTemperature();
        Double energyDiff = building.getResourceInfo(level + 1, temperature)
                .merge(building.getResourceInfo(level, temperature).negate())
                .getEnergy().amount();

        //todo фабрика роботов и наниты
        return BuildingDTO.builder()
                .building(building)
                .level(level)
                .buildingCost(buildingCost)
                .buildingTime(building.calculateBuildingTime(buildingCost))
                .energyDiff(energyDiff)
                .build();
    }
}
