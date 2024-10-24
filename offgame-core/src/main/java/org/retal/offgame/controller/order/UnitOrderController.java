package org.retal.offgame.controller.order;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.UnitOrderDTO;
import org.retal.offgame.dto.UnitOrderInfo;
import org.retal.offgame.service.UnitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UnitOrderController {

    private final UnitOrderService unitOrderService;

    @GetMapping("/construct/{planetId}/list")
    public List<UnitOrderInfo> getActiveUnitOrders(@PathVariable Long planetId) {
        return unitOrderService.getPlanetUnitOrders(planetId);
    }

    @PostMapping("/construct")
    public ResponseEntity<UnitOrderInfo> createUnitOrder(@RequestBody UnitOrderDTO unitOrderDTO) {
        UnitOrderInfo result = unitOrderService.createUnitOrder(unitOrderDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @DeleteMapping("/construct/{id}")
    public ResponseEntity<?> cancelUnitOrder(@PathVariable Long id) {
        unitOrderService.cancelUnitOrder(id);
        return ResponseEntity.noContent()
                .build();
    }
}
