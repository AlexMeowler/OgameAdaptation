package org.retal.offgame.controller;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.UserDTO;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserService userService;

    @GetMapping("/details")
    public UserDTO getUserDetails() {
        return userService.getAuthenticatedUserDetails();
    }

    @PostMapping("/activePlanet")
    public UserDTO setActivePlanet(@RequestBody Long planetId) {
        return userService.setActivePlanet(planetId);
    }
}
