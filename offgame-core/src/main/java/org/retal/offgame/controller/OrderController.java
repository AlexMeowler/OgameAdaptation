package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.service.BuildingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderController {

    private final BuildingOrderService buildingOrderService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        buildingOrderService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }
}
