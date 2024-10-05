package org.retal.offgame.service;

import org.retal.offgame.dto.TechnologyDTO;
import org.retal.offgame.entity.technologies.Technology;

import java.util.List;

public interface TechnologyService extends CrudService<Technology, Long> {

    List<TechnologyDTO> getPlayerTechnologies(Long planetId);
}
