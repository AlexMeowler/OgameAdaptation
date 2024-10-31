package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.PlanetItem;
import org.retal.offgame.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/galaxy")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GalaxyController {

    private final PlanetService planetService;

    @GetMapping("/list")
    public List<PlanetItem> getPlanetList(@RequestParam("galaxy") Long galaxy, @RequestParam("system") Long system) {
        return planetService.getPlanetsInSystem(galaxy, system);
    }
}
