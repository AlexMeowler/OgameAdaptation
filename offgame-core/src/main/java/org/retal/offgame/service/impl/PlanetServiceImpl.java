package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.User;
import org.retal.offgame.repository.PlanetRepository;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final UserService userService;

    @Override
    public Planet getPlanetInfo(Long planetId) {
        return planetRepository.findById(planetId)
                .orElse(null);
    }

    @Override
    public List<Planet> getPlanetList() {
        return userService.getAuthenticatedUser()
                .map(User::getId)
                .map(planetRepository::findByOwnerIdOrderByCreatedAtAsc)
                .orElse(emptyList());
    }
}
