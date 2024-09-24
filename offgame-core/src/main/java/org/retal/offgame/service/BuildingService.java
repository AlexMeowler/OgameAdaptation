package org.retal.offgame.service;

import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.entity.buildings.Building;

import java.util.List;

public interface BuildingService extends CrudService<Building, Long> {

    List<BuildingDTO> getPlanetBuildings(Long planetId);
}
