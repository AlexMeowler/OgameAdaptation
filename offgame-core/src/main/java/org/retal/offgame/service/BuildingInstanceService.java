package org.retal.offgame.service;

import org.retal.offgame.entity.BuildingInstance;

import java.util.Optional;

public interface BuildingInstanceService extends CrudService<BuildingInstance, Long> {

    Optional<BuildingInstance> findByPlanetIdAndBuildingId(Long planetId, Long buildingId);
}
