package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.repository.BuildingInstanceRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.BuildingInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingInstanceServiceImpl extends AbstractCrudService<BuildingInstance, Long> implements BuildingInstanceService {

    private final BuildingInstanceRepository buildingInstanceRepository;

    @Override
    public Optional<BuildingInstance> findByPlanetIdAndBuildingId(Long planetId, Long buildingId) {
        return buildingInstanceRepository.findByPlanetIdAndBuildingId(planetId, buildingId);
    }

    @Override
    protected CrudRepository<BuildingInstance, Long> getRepository() {
        return buildingInstanceRepository;
    }
}
