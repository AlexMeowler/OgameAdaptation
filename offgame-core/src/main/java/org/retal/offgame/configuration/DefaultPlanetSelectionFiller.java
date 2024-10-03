package org.retal.offgame.configuration;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.User;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toSet;

/**
 * Sets default active planets for all users that don't have one selected
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DefaultPlanetSelectionFiller implements CommandLineRunner {

    private final UserService userService;

    @Override
    @Transactional
    public void run(String... args) {
        Set<User> usersToUpdate = userService.getUsersWithoutSelectedPlanet().stream()
                .map(this::processUser)
                .collect(toSet());

        userService.saveAll(usersToUpdate);
    }

    private User processUser(User user) {
        Planet activePlanet = user.getPlanets().stream()
                .min(comparingLong(Planet::getId))
                .orElse(null); //todo generate planet otherwise

        user.setActivePlanet(activePlanet);

        return user;
    }
}
