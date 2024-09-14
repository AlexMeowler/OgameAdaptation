package org.retal.offgame.service;

import org.retal.offgame.dto.BuildingInstanceDTO;
import org.retal.offgame.entity.buildings.Building;

import java.util.List;
import java.util.Set;

public interface BuildingService {

    Set<BuildingInstanceDTO> getPlanetBuildings(Long planetId);

    List<Building> getBuildingList();
}
