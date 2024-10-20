package org.retal.offgame.repository;

import org.retal.offgame.entity.UnitInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitInstanceRepository extends JpaRepository<UnitInstance, Long> {

    Optional<UnitInstance> findByPlanetIdAndUnitId(Long planetId, Long unitId);
}
