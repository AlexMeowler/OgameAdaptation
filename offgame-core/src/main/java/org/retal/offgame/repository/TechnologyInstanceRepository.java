package org.retal.offgame.repository;

import org.retal.offgame.entity.TechnologyInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyInstanceRepository extends CrudRepository<TechnologyInstance, Long> {

    Optional<TechnologyInstance> findByOwnerIdAndTechnologyId(Long userId, Long technologyId);
}
