package org.retal.offgame.controller.order;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingOrderDTO;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.service.BuildingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingOrderController {

    private final BuildingOrderService buildingOrderService;

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
        buildingOrderService.cancelBuildingOrder(id);
        return ResponseEntity.noContent()
                .build();
    }
}
