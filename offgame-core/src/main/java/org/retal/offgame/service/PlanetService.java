package org.retal.offgame.service;

import org.retal.offgame.dto.PlanetItem;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.Upgradeable;

import java.util.List;
import java.util.Map;

public interface PlanetService extends CrudService<Planet, Long> {

    Planet getPlanetInfo(Long planetId);

    List<PlanetItem> getPlanetItemList();

    ResourcesDTO getResourcesInfo(Long planetId);

    Map<Class<? extends Upgradeable>, Long> getSpecialEntityLevels(Long planetId);
}
