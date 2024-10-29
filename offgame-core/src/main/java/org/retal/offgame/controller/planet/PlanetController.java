package org.retal.offgame.controller.planet;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.PlanetItem;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.dto.ResourcesDetails;
import org.retal.offgame.service.PlanetService;
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

    @GetMapping("/list")
    public List<PlanetItem> getPlanetList() {
        return planetService.getPlanetItemList();
    }

    @GetMapping("/{id}")
    public PlanetItem getPlanetInfo(@PathVariable Long id) {
        return planetService.getPlanetItemInfo(id);
    }

    @GetMapping("/{id}/resources")
    public ResourcesDTO getPlanetResources(@PathVariable Long id) {
        return planetService.getResourcesInfo(id);
    }

    @GetMapping("/{id}/resources/details")
    public ResourcesDetails getPlanetResourcesDetails(@PathVariable Long id) {
        return planetService.getResourcesDetails(id);
    }
}
