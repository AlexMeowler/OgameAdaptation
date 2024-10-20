package org.retal.offgame.service;

import org.retal.offgame.entity.UnitInstance;

import java.util.Optional;

public interface UnitInstanceService extends CrudService<UnitInstance, Long> {

    Optional<UnitInstance> findByPlanetIdAndUnitId(Long planetId, Long unitId);
}
