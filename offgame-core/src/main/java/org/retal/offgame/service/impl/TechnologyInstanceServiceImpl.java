package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.repository.TechnologyInstanceRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.TechnologyInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TechnologyInstanceServiceImpl extends AbstractCrudService<TechnologyInstance, Long> implements TechnologyInstanceService {

    private final TechnologyInstanceRepository technologyInstanceRepository;

    @Override
    public Optional<TechnologyInstance> findByPlanetIdAndTechnologyId(Long planetId, Long technologyId) {
        return technologyInstanceRepository.findByPlanetIdAndTechnologyId(planetId, technologyId);
    }

    @Override
    protected CrudRepository<TechnologyInstance, Long> getRepository() {
        return technologyInstanceRepository;
    }
}
