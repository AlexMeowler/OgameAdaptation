package org.retal.offgame.service;

import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.BuildingDetails;
import org.retal.offgame.entity.buildings.Building;

import java.util.List;
import java.util.Map;

public interface BuildingService extends CrudService<Building, Long> {

    List<BuildingDTO> getPlanetBuildings(Long planetId);

    BuildingDetails getBuildingDetails(Long planetId, Long buildingId);

    Map<Class<? extends Building>, Long> getSpecialBuildingLevels(Long planetId);
}
