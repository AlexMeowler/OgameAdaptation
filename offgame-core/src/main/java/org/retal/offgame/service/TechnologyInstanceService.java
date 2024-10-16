package org.retal.offgame.service;

import org.retal.offgame.entity.TechnologyInstance;

import java.util.Optional;

public interface TechnologyInstanceService extends CrudService<TechnologyInstance, Long> {

    Optional<TechnologyInstance> findByPlanetIdAndTechnologyId(Long planetId, Long technologyId);
}
