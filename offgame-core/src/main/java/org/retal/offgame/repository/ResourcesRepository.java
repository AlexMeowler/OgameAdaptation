package org.retal.offgame.repository;

import org.retal.offgame.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {

    Resources findByPlanetId(Long planetId);
}
