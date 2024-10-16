package org.retal.offgame.repository;

import org.retal.offgame.entity.TechnologyInstance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyInstanceRepository extends CrudRepository<TechnologyInstance, Long> {

    Optional<TechnologyInstance> findByOwnerIdAndTechnologyId(Long userId, Long technologyId);

    @Query("select ti from TechnologyInstance ti " +
            "join ti.technology t " +
            "join ti.owner.planets p " +
            "where p.id = :planetId and t.id = :technologyId")
    Optional<TechnologyInstance> findByPlanetIdAndTechnologyId(@Param("planetId") Long planetId, @Param("technologyId") Long technologyId);
}
