package org.retal.offgame.service.impl;

import org.retal.offgame.model.Role;
import org.retal.offgame.model.RoleType;
import org.retal.offgame.repository.RoleRepository;
import org.retal.offgame.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleType(RoleType type) {
        return roleRepository.findByName(type)
                .orElse(null);
    }
}
