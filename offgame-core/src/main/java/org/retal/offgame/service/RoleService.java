package org.retal.offgame.service;

import org.retal.offgame.model.Role;
import org.retal.offgame.model.RoleType;

public interface RoleService {

    Role findByRoleType(RoleType type);
}
