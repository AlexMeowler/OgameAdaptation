package org.retal.offgame.repository;

import org.retal.offgame.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    List<Planet> findByOwnerIdOrderByCreatedAtAsc(Long ownerId);

    //TODO security?

}
