package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.UnitDTO;
import org.retal.offgame.entity.units.UnitType;
import org.retal.offgame.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetUnitController {

    private final UnitService unitService;

    @GetMapping("/{id}/units")
    public List<UnitDTO> getPlanetUnitsInfo(@PathVariable Long id, @RequestParam("type") UnitType unitType) {
        return unitService.getPlanetUnits(id, unitType);
    }
}
