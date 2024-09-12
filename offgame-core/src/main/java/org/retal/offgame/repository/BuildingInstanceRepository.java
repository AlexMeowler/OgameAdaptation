package org.retal.offgame.repository;

import jakarta.validation.constraints.NotNull;
import org.retal.offgame.dto.BuildingInstanceDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BuildingInstanceRepository extends JpaRepository<BuildingInstance, Long> {

    Set<BuildingInstanceDTO> findByPlanetId(@NotNull Long planetId);
}
