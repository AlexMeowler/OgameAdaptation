package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.*;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.service.BuildingOrderService;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetController {

    private final PlanetService planetService;
    private final BuildingService buildingService;
    private final BuildingOrderService buildingOrderService;
    private final TechnologyService technologyService;

    @GetMapping("/{id}")
    public Planet getPlanetInfo(@PathVariable Long id) {
        return planetService.getPlanetInfo(id);
    }

    @GetMapping("/{id}/resources")
    public ResourcesDTO getPlanetResources(@PathVariable Long id) {
        return planetService.getResourcesInfo(id);
    }

    @GetMapping("/{id}/buildings")
    public List<BuildingDTO> getPlanetBuildingsInfo(@PathVariable Long id) {
        return buildingService.getPlanetBuildings(id);
    }

    @GetMapping("/{id}/technologies")
    public List<TechnologyDTO> getPlanetTechnologiesInfo(@PathVariable Long id) {
        return technologyService.getPlayerTechnologies(id);
    }

    @GetMapping("/{planetId}/buildings/{buildingId}/details")
    public BuildingDetails getPlanetBuildingsInfo(@PathVariable Long planetId, @PathVariable Long buildingId) {
        return buildingService.getBuildingDetails(planetId, buildingId);
    }

    @GetMapping("/list")
    public List<PlanetItem> getPlanetList() {
        return planetService.getPlanetItemList();
    }
}
