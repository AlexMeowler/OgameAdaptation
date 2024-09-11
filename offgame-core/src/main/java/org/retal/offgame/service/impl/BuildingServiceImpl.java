package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.model.Building;
import org.retal.offgame.repository.BuildingRepository;
import org.retal.offgame.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;

    @Override
    public List<Building> getAllBuildings() {
        return repository.findAll(Sort.by("id").ascending());
    }
}
