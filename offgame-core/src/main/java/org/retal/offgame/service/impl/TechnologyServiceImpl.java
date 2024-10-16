package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.TechnologyDTO;
import org.retal.offgame.dto.TechnologyDetails;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.User;
import org.retal.offgame.entity.technologies.Technology;
import org.retal.offgame.repository.TechnologyRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.TechnologyInstanceService;
import org.retal.offgame.service.TechnologyService;
import org.retal.offgame.service.util.RequirementUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TechnologyServiceImpl extends AbstractCrudService<Technology, Long> implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final PlanetService planetService;
    private final TechnologyInstanceService technologyInstanceService;

    @Override
    @Transactional
    public List<TechnologyDTO> getPlayerTechnologies(Long planetId) {
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planetId);
        User owner = planetService.getPlanetInfo(planetId).getOwner();

        return owner.getTechnologies().stream()
                .sorted(comparingLong(TechnologyInstance::getTechnologyId))
                .map(technology -> toDTO(technology, specialEntityLevels))
                .collect(toList());
    }

    private TechnologyDTO toDTO(TechnologyInstance technologyInstance, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        Long level = technologyInstance.getLevel();
        Technology technology = technologyInstance.getTechnology();

        return TechnologyDTO.builder()
                .technology(technology)
                .level(level)
                .researchCost(technology.calculateBuildingCost(level + 1))
                .researchTime(technology.calculateBuildingTime(level + 1, specialEntityLevels))
                .requirements(RequirementUtils.getRequirements(technology.getRequirements(), specialEntityLevels))
                .build();
    }

    @Override
    @Transactional
    public TechnologyDetails getTechnologyDetails(Long planetId, Long technologyId) {
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planetId);
        return technologyInstanceService.findByPlanetIdAndTechnologyId(planetId, technologyId)
                .map(technologyInstance -> toDetails(technologyInstance, specialEntityLevels))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private TechnologyDetails toDetails(TechnologyInstance technologyInstance, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        Technology technology = technologyInstance.getTechnology();

        return TechnologyDetails.builder()
                .name(technology.getName())
                .description(technology.getFullDescription())
                .imageName(technology.getImageName())
                .requirements(RequirementUtils.getRequirements(technology.getRequirements(), specialEntityLevels))
                .build();
    }

    @Override
    protected CrudRepository<Technology, Long> getRepository() {
        return technologyRepository;
    }
}
