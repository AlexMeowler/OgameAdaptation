package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingDTO;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.repository.BuildingRepository;
import org.retal.offgame.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Override
    public List<BuildingDTO> getPlanetBuildings(Long planetId) {
        return buildingRepository.findByPlanetId(planetId).stream()
                .map(Building::toDTO)
                .collect(Collectors.toList());
    }
}
