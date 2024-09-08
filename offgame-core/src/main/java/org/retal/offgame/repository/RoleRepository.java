package org.retal.offgame.repository;

import org.retal.offgame.model.Role;
import org.retal.offgame.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}
