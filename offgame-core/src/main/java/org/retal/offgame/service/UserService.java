package org.retal.offgame.service;

import org.retal.offgame.entity.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {

    Optional<User> getAuthenticatedUser();
}
