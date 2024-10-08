package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.TechnologyDTO;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.entity.User;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.entity.technologies.Technology;
import org.retal.offgame.repository.TechnologyRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TechnologyServiceImpl extends AbstractCrudService<Technology, Long> implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final PlanetService planetService;
    private final BuildingService buildingService;

    @Override
    @Transactional
    public List<TechnologyDTO> getPlayerTechnologies(Long planetId) {
        Map<Class<? extends Building>, Long> specialBuildingLevels = buildingService.getSpecialBuildingLevels(planetId);
        User owner = planetService.getPlanetInfo(planetId).getOwner();

        return technologyRepository.findByUser(owner).stream()
                .map(technology -> toDTO(technology, owner, specialBuildingLevels))
                .collect(toList());
    }

    private TechnologyDTO toDTO(Technology technology, User user, Map<Class<? extends Building>, Long> specialBuildingLevels) {
        Long level = getLevel(technology, user);

        return TechnologyDTO.builder()
                .technology(technology)
                .level(level)
                .researchCost(technology.calculateBuildingCost(level + 1))
                .researchTime(technology.calculateBuildingTime(level + 1, specialBuildingLevels))
                .build();
    }

    private Long getLevel(Technology technology, User user) {
        return Optional.ofNullable(technology)
                .map(Technology::getInstances)
                .flatMap(instances -> instances.stream()
                        .filter(instance -> instance.getOwner().equals(user))
                        .findFirst())
                .map(TechnologyInstance::getLevel)
                .orElse(0L);
    }

    @Override
    protected CrudRepository<Technology, Long> getRepository() {
        return technologyRepository;
    }
}
