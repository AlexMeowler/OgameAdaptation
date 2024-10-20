package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.TechnologyDTO;
import org.retal.offgame.dto.TechnologyDetails;
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
public class PlanetTechnologyController {

    private final TechnologyService technologyService;

    @GetMapping("/{id}/technologies")
    public List<TechnologyDTO> getPlanetTechnologiesInfo(@PathVariable Long id) {
        return technologyService.getPlayerTechnologies(id);
    }

    @GetMapping("/{planetId}/technologies/{technologyId}/details")
    public TechnologyDetails getPlanetTechnologyDetails(@PathVariable Long planetId, @PathVariable Long technologyId) {
        return technologyService.getTechnologyDetails(planetId, technologyId);
    }
}
