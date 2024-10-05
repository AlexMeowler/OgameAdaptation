package org.retal.offgame.repository;

import jakarta.validation.constraints.NotNull;
import org.retal.offgame.entity.User;
import org.retal.offgame.entity.technologies.Technology;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {

    @Query("select t from Technology t left join fetch t.instances ti where (ti is null or ti.owner = :owner) order by t.id")
    List<Technology> findByUser(@NotNull @Param("owner") User owner);
}
