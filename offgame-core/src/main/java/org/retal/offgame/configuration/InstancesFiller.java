package org.retal.offgame.configuration;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.entity.User;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.entity.technologies.Technology;
import org.retal.offgame.service.*;
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
public class InstancesFiller implements CommandLineRunner {

    private final BuildingService buildingService;
    private final PlanetService planetService;
    private final BuildingInstanceService buildingInstanceService;
    private final UserService userService;
    private final TechnologyService technologyService;
    private final TechnologyInstanceService technologyInstanceService;

    @Override
    public void run(String... args) {
        createMissingBuildingInstances();
        createMissingTechnologyInstances();
    }

    private void createMissingBuildingInstances() {
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

    private void createMissingTechnologyInstances() {
        Collection<Technology> technologies = technologyService.findAll();
        Set<TechnologyInstance> instancesToCreate = userService.findAll().stream()
                .flatMap(user -> findMissingTechnologyInstances(user, technologies))
                .collect(toSet());

        technologyInstanceService.saveAll(instancesToCreate);
    }

    private Stream<TechnologyInstance> findMissingTechnologyInstances(User user, Collection<Technology> technologyList) {
        Set<Technology> technologies = user.getTechnologies().stream()
                .map(TechnologyInstance::getTechnology)
                .collect(toSet());

        return technologies.stream()
                .filter(not(technologies::contains))
                .map(technology -> toTechnologyInstance(user, technology));
    }

    private TechnologyInstance toTechnologyInstance(User user, Technology technology) {
        return TechnologyInstance.builder()
                .owner(user)
                .technology(technology)
                .level(0L)
                .build();
    }
}
