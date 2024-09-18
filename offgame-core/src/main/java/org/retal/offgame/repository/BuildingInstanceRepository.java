package org.retal.offgame.repository;

import org.retal.offgame.entity.BuildingInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingInstanceRepository extends JpaRepository<BuildingInstance, Long> {
}
