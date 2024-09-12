package org.retal.offgame.service;

import org.retal.offgame.entity.User;

import java.util.Optional;

public interface UserService {

    User saveOrUpdate(User user);

    Optional<User> getAuthenticatedUser();
}
