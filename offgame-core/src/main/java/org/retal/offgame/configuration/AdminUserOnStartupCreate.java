package org.retal.offgame.configuration;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.Role;
import org.retal.offgame.entity.User;
import org.retal.offgame.service.RoleService;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Collections.singleton;
import static org.retal.offgame.entity.RoleType.ROLE_ADMIN;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdminUserOnStartupCreate implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Value("${spring.security.user.name}")
    private String defaultUsername;

    @Value("${spring.security.user.password}")
    private String defaultPassword;

    @Override
    public void run(String... args) {
        Role adminRole = roleService.findByRoleType(ROLE_ADMIN);
        User admin = User.builder()
                .id(1L)
                .username(defaultUsername)
                .password(defaultPassword)
                .roles(singleton(adminRole))
                .build();

        userService.saveOrUpdate(admin);
    }
}
