package org.retal.offgame.controller.planet;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.dto.BuildingDetails;
import org.retal.offgame.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetBuildingController {

    private final BuildingService buildingService;

    @GetMapping("/{id}/buildings")
    public List<BuildingDTO> getPlanetBuildingsInfo(@PathVariable Long id) {
        return buildingService.getPlanetBuildings(id);
    }

    @GetMapping("/{id}/buildings/{buildingId}/details")
    public BuildingDetails getPlanetBuildingDetails(@PathVariable Long id, @PathVariable Long buildingId) {
        return buildingService.getBuildingDetails(id, buildingId);
    }

    @PostMapping("/{id}/resources/efficiency")
    public ResponseEntity<?> updateEfficiency(@PathVariable Long id, @RequestBody Map<Long, Double> efficiencyMap) {
        buildingService.updateBuildingsEfficiency(id, efficiencyMap);

        return ResponseEntity.noContent().build();
    }
}
