package org.retal.offgame.service;

import org.retal.offgame.dto.PlanetItem;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Planet;

import java.util.List;

public interface PlanetService extends CrudService<Planet, Long> {

    Planet getPlanetInfo(Long planetId);

    List<PlanetItem> getPlanetItemList();

    ResourcesDTO getResourcesInfo(Long planetId);
}
