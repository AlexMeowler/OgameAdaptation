package org.retal.offgame.controller.order;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.TechnologyOrderDTO;
import org.retal.offgame.dto.TechnologyOrderInfo;
import org.retal.offgame.service.TechnologyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TechnologyOrderController {

    private final TechnologyOrderService technologyOrderService;

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
        technologyOrderService.cancelTechnologyOrder(id);
        return ResponseEntity.noContent()
                .build();
    }
}
