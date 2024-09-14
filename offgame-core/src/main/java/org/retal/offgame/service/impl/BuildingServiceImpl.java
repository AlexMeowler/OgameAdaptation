package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.BuildingInstanceDTO;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.repository.BuildingInstanceRepository;
import org.retal.offgame.repository.BuildingRepository;
import org.retal.offgame.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingServiceImpl implements BuildingService {

    private final BuildingInstanceRepository buildingInstanceRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public Set<BuildingInstanceDTO> getPlanetBuildings(Long planetId) {
        return buildingInstanceRepository.findByPlanetId(planetId);
    }

    @Override
    public List<Building> getBuildingList() {
        return buildingRepository.findAllByOrderByIdAsc();
    }
}
