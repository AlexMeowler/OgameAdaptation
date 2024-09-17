package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.Resources;
import org.retal.offgame.entity.User;
import org.retal.offgame.repository.PlanetRepository;
import org.retal.offgame.repository.ResourcesRepository;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final ResourcesRepository resourcesRepository;

    private final UserService userService;

    @Override
    public Planet getPlanetInfo(Long planetId) {
        return planetRepository.findById(planetId)
                .orElse(null);
    }

    @Override
    public List<Planet> getPlanetList() {
        return userService.getAuthenticatedUser()
                .map(User::getId)
                .map(planetRepository::findByOwnerIdOrderByCreatedAtAsc)
                .orElse(emptyList());
    }

    @Override
    @Transactional
    public ResourcesDTO getResourcesInfo(Long planetId) {
        return planetRepository.findById(planetId)
                .map(this::updateResources)
                .get();
    }

    private ResourcesDTO updateResources(Planet planet) {
        Integer temperature = planet.averageTemperature();
        ResourcesDTO totalProductionPerHourWithLimit = planet.getBuildings().stream()
                .map(instance -> instance.getBuilding().getResourceInfo(instance.getLevel(), temperature))
                .reduce(ResourcesDTO.defaultDTO(), ResourcesDTO::merge);

        Resources resources = planet.getResources();
        Duration duration = Duration.between(resources.getUpdatedAt(), Instant.now());

        resources.updateResources(totalProductionPerHourWithLimit, duration);
        resourcesRepository.save(resources);

        return totalProductionPerHourWithLimit.merge(resources.toDTO());
    }
}
