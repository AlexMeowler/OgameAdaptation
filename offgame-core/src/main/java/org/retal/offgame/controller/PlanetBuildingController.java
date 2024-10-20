package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.BuildingDetails;
import org.retal.offgame.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetBuildingController {

    private final BuildingService buildingService;

    @GetMapping("/{id}/buildings")
    public List<BuildingDTO> getPlanetBuildingsInfo(@PathVariable Long id) {
        return buildingService.getPlanetBuildings(id);
    }

    @GetMapping("/{planetId}/buildings/{buildingId}/details")
    public BuildingDetails getPlanetBuildingDetails(@PathVariable Long planetId, @PathVariable Long buildingId) {
        return buildingService.getBuildingDetails(planetId, buildingId);
    }
}
