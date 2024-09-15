package org.retal.offgame.service;

import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Planet;

import java.util.List;

public interface PlanetService {

    Planet getPlanetInfo(Long planetId);

    List<Planet> getPlanetList();

    ResourcesDTO getResourcesInfo(Long planetId);
}
