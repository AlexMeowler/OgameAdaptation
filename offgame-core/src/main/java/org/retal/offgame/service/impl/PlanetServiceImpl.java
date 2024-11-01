package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.*;
import org.retal.offgame.entity.*;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.entity.technologies.Technology;
import org.retal.offgame.repository.PlanetRepository;
import org.retal.offgame.repository.ResourcesRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetServiceImpl extends AbstractCrudService<Planet, Long> implements PlanetService {

    private final PlanetRepository planetRepository;
    private final ResourcesRepository resourcesRepository;
    private final UserService userService;

    @Override
    @Transactional
    public PlanetItem getPlanetItemInfo(Long planetId) {
        return planetRepository.findById(planetId)
                .map(this::toPlanetItem)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Planet getPlanetInfo(Long planetId) {
        return planetRepository.findById(planetId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public List<PlanetItem> getPlanetItemList() {
        return userService.getAuthenticatedUser()
                .map(User::getId)
                .map(planetRepository::findByOwnerIdOrderByCreatedAtAsc)
                .map(planets -> planets.stream()
                        .map(this::toPlanetItem)
                        .collect(toList()))
                .orElse(emptyList());
    }

    @Override
    @Transactional
    public List<PlanetItem> getPlanetsInSystem(Long galaxy, Long system) {
        return planetRepository.findByGalaxyAndSystemOrderByPositionAsc(galaxy, system).stream()
                .map(this::toPlanetItem)
                .collect(toList());
    }

    private PlanetItem toPlanetItem(Planet planet) {
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = getSpecialEntityLevels(planet.getId());

        return PlanetItem.builder()
                .id(planet.getId())
                .name(planet.getName())
                .imageName(planet.getImageName())
                .totalFields(planet.getTotalFields(specialEntityLevels))
                .usedFields(planet.getUsedFields(specialEntityLevels))
                .minTemperature(planet.getMinTemperature())
                .maxTemperature(planet.getMaxTemperature())
                .galaxy(planet.getGalaxy())
                .system(planet.getSystem())
                .position(planet.getPosition())
                .build();
    }

    @Override
    @Transactional
    public ResourcesDTO getResourcesInfo(Long planetId) {
        return planetRepository.findById(planetId)
                .map(this::updateResources)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private ResourcesDTO updateResources(Planet planet) {
        Map<Class<? extends Upgradeable>, Long> specialBuildingLevels = getSpecialEntityLevels(planet.getId());
        Long temperature = planet.getMaxTemperature();
        ResourcesDTO totalProductionPerHourWithLimit = planet.getBuildings().stream()
                .map(instance -> instance.getBuilding().getResourceInfo(instance.getLevel(), instance.getEfficiency(), temperature, specialBuildingLevels))
                .reduce(ResourcesDTO.empty(), ResourcesDTO::merge);
        totalProductionPerHourWithLimit.setGlobalEfficiency();

        double globalEfficiency = totalProductionPerHourWithLimit.getGlobalEfficiency();
        adjustProductionPerHour(totalProductionPerHourWithLimit, globalEfficiency);
        totalProductionPerHourWithLimit = totalProductionPerHourWithLimit.merge(ResourcesDTO.defaultDTO());

        Resources resources = planet.getResources();
        Duration duration = Duration.between(resources.getUpdatedAt(), Instant.now());

        resources.updateResources(totalProductionPerHourWithLimit, duration);
        resourcesRepository.save(resources);

        return totalProductionPerHourWithLimit.merge(resources.toDTO());
    }

    private void adjustProductionPerHour(ResourcesDTO resources, double globalEfficiency) {
        resources.ACCESSOR_MAP.keySet().stream()
                .map(getter -> getter.apply(resources))
                .forEach(resourceDTO -> {
                    if (resourceDTO.getProductionPerHour() > 0) {
                        resourceDTO.setProductionPerHour(resourceDTO.getProductionPerHour() * globalEfficiency);
                    }
                });
    }

    @Override
    @Transactional
    public ResourcesDetails getResourcesDetails(Long planetId) {
        Planet planet = getPlanetInfo(planetId);
        ResourcesDTO totalResources = getResourcesInfo(planetId);
        return ResourcesDetails.builder()
                .planetName(planet.getName())
                .resourceDetails(getResourceDetails(planet, totalResources.getGlobalEfficiency()))
                .totalResources(totalResources)
                .build();
    }

    private List<ResourceDetail> getResourceDetails(Planet planet, Double globalEfficiency) {
        List<ResourceDetail> resourceDetails = new ArrayList<>();
        resourceDetails.add(ResourceDetail.builder().resources(ResourcesDTO.defaultDTO()).build());

        Map<Class<? extends Upgradeable>, Long> specialBuildingLevels = getSpecialEntityLevels(planet.getId());
        Long temperature = planet.getMaxTemperature();
        //TODO calc units
        planet.getBuildings().stream()
                .filter(instance -> instance.getLevel() > 0)
                .filter(instance -> !instance.getBuilding().getResourceInfo(instance.getLevel(), 1.0, temperature, specialBuildingLevels).isEmpty(ResourceDTO::getProductionPerHour, ResourceDTO::getAmount))
                .sorted(Comparator.comparingLong(BuildingInstance::getBuildingId))
                .map(instance -> ResourceDetail.builder()
                        .id(instance.getBuilding().getId())
                        .name(instance.getBuilding().getName())
                        .level(instance.getLevel())
                        .resources(instance.getBuilding().getResourceInfo(instance.getLevel(), instance.getEfficiency(), temperature, specialBuildingLevels))
                        .build())
                .peek(detail -> adjustProductionPerHour(detail.getResources(), globalEfficiency))
                .forEach(resourceDetails::add);

        return resourceDetails;
    }

    @Override
    @Transactional
    public Map<Class<? extends Upgradeable>, Long> getSpecialEntityLevels(Long planetId) {
        Planet planet = getPlanetInfo(planetId);

        Stream<AbstractMap.SimpleEntry<Class<? extends Upgradeable>, Long>> buildings = planet.getBuildings().stream()
                .filter(buildingInstance -> buildingInstance.getBuilding().getClass() != Building.class)
                .map(buildingInstance -> new AbstractMap.SimpleEntry<>(buildingInstance.getBuilding().getClass(), buildingInstance.getLevel()));

        Stream<AbstractMap.SimpleEntry<Class<? extends Upgradeable>, Long>> technologies = planet.getOwner().getTechnologies().stream()
                .filter(technologyInstance -> technologyInstance.getTechnology().getClass() != Technology.class)
                .map(technologyInstance -> new AbstractMap.SimpleEntry<>(technologyInstance.getTechnology().getClass(), technologyInstance.getLevel()));

        return Stream.of(buildings, technologies)
                .flatMap(Function.identity())
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    @Override
    protected CrudRepository<Planet, Long> getRepository() {
        return planetRepository;
    }
}
