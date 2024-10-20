package org.retal.offgame.service;

import org.retal.offgame.dto.UnitDTO;
import org.retal.offgame.entity.units.Unit;
import org.retal.offgame.entity.units.UnitType;

import java.util.List;

public interface UnitService extends CrudService<Unit, Long> {

    List<UnitDTO> getPlanetUnits(Long planetId, UnitType type);
}
