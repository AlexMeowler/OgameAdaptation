package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.BuildingDetails;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.repository.BuildingRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingServiceImpl extends AbstractCrudService<Building, Long> implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final PlanetService planetService;

    @Override
    public List<BuildingDTO> getPlanetBuildings(Long planetId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        List<Building> buildings = buildingRepository.findByPlanetId(planetId);
        Map<Class<? extends Building>, Long> specialBuildingLevels = buildings.stream()
                .filter(building -> building.getClass() != Building.class)
                .collect(toMap(
                        Building::getClass,
                        building -> getLevel(building, planet)
                ));

        return buildings.stream()
                .map(building -> toDTO(building, planet, specialBuildingLevels))
                .collect(Collectors.toList());
    }

    private BuildingDTO toDTO(Building building, Planet planet, Map<Class<? extends Building>, Long> specialBuildingLevels) {
        Long level = getLevel(building, planet);
        int temperature = planet.averageTemperature();
        Double energyDiff = building.getResourceInfo(level + 1, temperature)
                .merge(building.getResourceInfo(level, temperature).negate())
                .getEnergy().amount();

        return BuildingDTO.builder()
                .building(building)
                .level(level)
                .buildingCost(building.calculateBuildingCost(level + 1))
                .buildingTime(building.calculateBuildingTime(level + 1, specialBuildingLevels))
                .energyDiff(energyDiff)
                .build();
    }

    private Long getLevel(Building building, Planet planet) {
        return Optional.ofNullable(building)
                .map(Building::getInstances)
                .flatMap(instances -> instances.stream()
                        .filter(instance -> instance.getPlanet().equals(planet))
                        .findFirst())
                .map(BuildingInstance::getLevel)
                .orElse(0L);
    }

    @Override
    @Transactional
    public BuildingDetails getBuildingDetails(Long planetId, Long buildingId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        return buildingRepository.findById(buildingId)
                .map(building -> toDetails(building, planet))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private BuildingDetails toDetails(Building building, Planet planet) {
        Long level = getLevel(building, planet);
        int temperature = planet.averageTemperature();
        Long a = Math.max(1, level - 2);
        Long b = level + 10;

        Map<Long, ResourcesDTO> productionByLevel = LongStream.range(a, b + 1)
                .boxed()
                .map(i -> Pair.of(i, building.getResourceInfo(i, temperature)))
                .filter(pair -> !pair.getSecond().isEmpty())
                .collect(toMap(
                        Pair::getFirst,
                        Pair::getSecond
                ));

        ResourcesDTO currentProduction = productionByLevel.getOrDefault(level, ResourcesDTO.empty());
        Map<Long, ResourcesDTO> differenceByLevel = productionByLevel.entrySet().stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        entry -> currentProduction.copy().negate().merge(entry.getValue())
                ));

        return BuildingDetails.builder()
                .name(building.getName())
                .description(building.getFullDescription())
                .imageName(building.getImageName())
                .currentLevel(level)
                .productionByLevel(productionByLevel)
                .differenceByLevel(differenceByLevel)
                .build();
    }

    @Override
    public Map<Class<? extends Building>, Long> getSpecialBuildingLevels(Long planetId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        List<Building> buildings = buildingRepository.findByPlanetId(planetId);
        return buildings.stream()
                .filter(building -> building.getClass() != Building.class)
                .collect(toMap(
                        Building::getClass,
                        building -> getLevel(building, planet)
                ));
    }

    @Override
    protected CrudRepository<Building, Long> getRepository() {
        return buildingRepository;
    }
}
