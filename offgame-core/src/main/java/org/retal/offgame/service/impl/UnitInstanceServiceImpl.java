package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.UnitInstance;
import org.retal.offgame.repository.UnitInstanceRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.UnitInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UnitInstanceServiceImpl extends AbstractCrudService<UnitInstance, Long> implements UnitInstanceService {

    private final UnitInstanceRepository unitInstanceRepository;

    @Override
    public Optional<UnitInstance> findByPlanetIdAndUnitId(Long planetId, Long unitId) {
        return unitInstanceRepository.findByPlanetIdAndUnitId(planetId, unitId);
    }

    @Override
    protected CrudRepository<UnitInstance, Long> getRepository() {
        return unitInstanceRepository;
    }
}
