package org.retal.offgame.repository;

import org.retal.offgame.entity.technologies.Technology;
import org.springframework.data.repository.CrudRepository;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {
}
