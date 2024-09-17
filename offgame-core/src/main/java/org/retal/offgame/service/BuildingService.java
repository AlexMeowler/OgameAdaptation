package org.retal.offgame.service;

import org.retal.offgame.dto.BuildingDTO;

import java.util.List;

public interface BuildingService {

    List<BuildingDTO> getPlanetBuildings(Long planetId);

}
