package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.BuildingDetails;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.repository.BuildingRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.BuildingInstanceService;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.util.RequirementUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingServiceImpl extends AbstractCrudService<Building, Long> implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingInstanceService buildingInstanceService;
    private final PlanetService planetService;

    @Override
    @Transactional
    public List<BuildingDTO> getPlanetBuildings(Long planetId) {
        Planet planet = planetService.getPlanetInfo(planetId);
        Set<BuildingInstance> buildingInstances = planet.getBuildings();
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planetId);

        return buildingInstances.stream()
                .sorted(comparingLong(BuildingInstance::getBuildingId))
                .map(buildingInstance -> toDTO(buildingInstance, specialEntityLevels))
                .collect(Collectors.toList());
    }

    private BuildingDTO toDTO(BuildingInstance buildingInstance, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        Long level = buildingInstance.getLevel();
        int temperature = buildingInstance.getPlanet().averageTemperature();
        Building building = buildingInstance.getBuilding();
        Double energyDiff = building.getResourceInfo(level + 1, temperature)
                .merge(building.getResourceInfo(level, temperature).negate())
                .getEnergy().amount();

        return BuildingDTO.builder()
                .building(building)
                .level(level)
                .buildingCost(building.calculateBuildingCost(level + 1))
                .buildingTime(building.calculateBuildingTime(level + 1, specialEntityLevels))
                .energyDiff(energyDiff)
                .requirements(RequirementUtils.getRequirements(building.getRequirements(), specialEntityLevels))
                .build();
    }

    @Override
    @Transactional
    public BuildingDetails getBuildingDetails(Long planetId, Long buildingId) {
        return buildingInstanceService.findByPlanetIdAndBuildingId(planetId, buildingId)
                .map(this::toDetails)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private BuildingDetails toDetails(BuildingInstance buildingInstance) {
        Long level = buildingInstance.getLevel();
        int temperature = buildingInstance.getPlanet().averageTemperature();
        Long a = Math.max(1, level - 2);
        Long b = level + 10;
        Building building = buildingInstance.getBuilding();

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
    protected CrudRepository<Building, Long> getRepository() {
        return buildingRepository;
    }
}
