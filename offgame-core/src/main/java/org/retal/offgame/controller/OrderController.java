package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingOrderDTO;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.dto.TechnologyOrderDTO;
import org.retal.offgame.dto.TechnologyOrderInfo;
import org.retal.offgame.service.BuildingOrderService;
import org.retal.offgame.service.TechnologyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderController {

    private final BuildingOrderService buildingOrderService;
    private final TechnologyOrderService technologyOrderService;

    @GetMapping("/build/{planetId}/list")
    public List<BuildingOrderInfo> getActiveBuildingOrders(@PathVariable Long planetId) {
        return buildingOrderService.getPlanetBuildingOrders(planetId);
    }

    @PostMapping("/build")
    public ResponseEntity<BuildingOrderInfo> createBuildingOrder(@RequestBody BuildingOrderDTO buildingOrderDTO) {
        BuildingOrderInfo result = buildingOrderService.createBuildingOrder(buildingOrderDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @DeleteMapping("/build/{id}")
    public ResponseEntity<?> cancelBuildingOrder(@PathVariable Long id) {
        buildingOrderService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/research/{planetId}/order")
    public TechnologyOrderInfo getActiveTechnologyOrder(@PathVariable Long planetId) {
        return technologyOrderService.getTechnologyOrder(planetId);
    }

    @PostMapping("/research")
    public ResponseEntity<TechnologyOrderInfo> createTechnologyOrder(@RequestBody TechnologyOrderDTO technologyOrderDTO) {
        TechnologyOrderInfo result = technologyOrderService.createTechnologyOrder(technologyOrderDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @DeleteMapping("/research/{id}")
    public ResponseEntity<?> cancelTechnologyOrder(@PathVariable Long id) {
        technologyOrderService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }
}
