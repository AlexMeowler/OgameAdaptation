package org.retal.offgame.repository;

import jakarta.validation.constraints.NotNull;
import org.retal.offgame.entity.buildings.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query("select b from Building b left join fetch b.instances bi where (bi is null or bi.planet.id = :planetId) order by b.id")
    List<Building> findByPlanetId(@NotNull @Param("planetId") Long planetId);
}
