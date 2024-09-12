package org.retal.offgame.service;

import org.retal.offgame.entity.Role;
import org.retal.offgame.entity.RoleType;

public interface RoleService {

    Role findByRoleType(RoleType type);
}
