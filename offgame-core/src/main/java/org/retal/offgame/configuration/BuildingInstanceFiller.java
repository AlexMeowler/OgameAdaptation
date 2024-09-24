package org.retal.offgame.configuration;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.service.BuildingInstanceService;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;

/**
 * Creates missing {@link BuildingInstance} entities for every planet on app startup with default level (0)
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingInstanceFiller implements CommandLineRunner {

    private final BuildingService buildingService;
    private final PlanetService planetService;
    private final BuildingInstanceService buildingInstanceService;

    @Override

    public void run(String... args) {
        Collection<Building> buildings = buildingService.findAll();
        Set<BuildingInstance> instancesToCreate = planetService.findAll().stream()
                .flatMap(planet -> findMissingBuildingInstances(planet, buildings))
                .collect(toSet());

        buildingInstanceService.saveAll(instancesToCreate);
    }

    private Stream<BuildingInstance> findMissingBuildingInstances(Planet planet, Collection<Building> buildingList) {
        Set<Building> planetBuildings = planet.getBuildings().stream()
                .map(BuildingInstance::getBuilding)
                .collect(toSet());

        return buildingList.stream()
                .filter(not(planetBuildings::contains))
                .map(building -> toBuildingInstance(planet, building));
    }

    private BuildingInstance toBuildingInstance(Planet planet, Building building) {
        return BuildingInstance.builder()
                .planet(planet)
                .building(building)
                .level(0L)
                .build();
    }
}
