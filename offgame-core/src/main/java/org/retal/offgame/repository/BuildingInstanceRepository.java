package org.retal.offgame.repository;

import org.retal.offgame.entity.BuildingInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingInstanceRepository extends JpaRepository<BuildingInstance, Long> {

    Optional<BuildingInstance> findByPlanetIdAndBuildingId(Long planetId, Long buildingId);
}
