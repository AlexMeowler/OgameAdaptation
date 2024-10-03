package org.retal.offgame.service;

import org.retal.offgame.dto.UserDTO;
import org.retal.offgame.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    Optional<User> getAuthenticatedUser();

    UserDTO getAuthenticatedUserDetails();

    UserDTO setActivePlanet(Long planetId);

    List<User> getUsersWithoutSelectedPlanet();
}
