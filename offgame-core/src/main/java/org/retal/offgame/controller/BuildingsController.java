package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.model.Building;
import org.retal.offgame.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingsController {

    private final BuildingService buildingService;

    @GetMapping("/list")
    public List<Building> getBuildingsList() {
        return buildingService.getAllBuildings();
    }
}
