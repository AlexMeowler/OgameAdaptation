package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.model.Role;
import org.retal.offgame.model.RoleType;
import org.retal.offgame.repository.RoleRepository;
import org.retal.offgame.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role findByRoleType(RoleType type) {
        return roleRepository.findByName(type)
                .orElse(null);
    }
}
