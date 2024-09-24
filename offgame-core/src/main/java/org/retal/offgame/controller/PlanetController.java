package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.BuildingOrderDTO;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.service.BuildingOrderService;
import org.retal.offgame.service.BuildingService;
import org.retal.offgame.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetController {

    private final PlanetService planetService;
    private final BuildingService buildingService;
    private final BuildingOrderService buildingOrderService;

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

    @GetMapping("/list")
    public List<Planet> getPlanetList() {
        return planetService.getPlanetList();
    }

    @PostMapping("/build")
    public ResponseEntity<BuildingOrderInfo> createBuildingOrder(@RequestBody BuildingOrderDTO buildingOrderDTO) {
        BuildingOrderInfo result = buildingOrderService.createBuildingOrder(buildingOrderDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/{id}/orders")
    public List<BuildingOrderInfo> getActiveOrders(@PathVariable Long id) {
        return buildingOrderService.getPlanetBuildingOrders(id);
    }
}
