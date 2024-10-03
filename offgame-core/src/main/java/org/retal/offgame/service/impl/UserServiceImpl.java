package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.UserDTO;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.User;
import org.retal.offgame.repository.UserRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl extends AbstractCrudService<User, Long> implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User saveOrUpdate(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return super.saveOrUpdate(user);
    }

    @Override
    public Optional<User> getAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(userRepository::findByUsername);
    }

    @Override
    public UserDTO getAuthenticatedUserDetails() {
        return getAuthenticatedUser()
                .map(this::toUserDTO)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private User getAuthenticatedUserOrThrow() {
        return getAuthenticatedUser().orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    private UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .userName(user.getUsername())
                .activePlanet(user.getActivePlanet().getId())
                .build();
    }

    @Override
    public UserDTO setActivePlanet(Long planetId) {
        User user = getAuthenticatedUserOrThrow();
        Planet activePlanet = user.getPlanets().stream()
                .filter(planet -> Objects.equals(planet.getId(), planetId))
                .findFirst()
                .orElse(null);

        user.setActivePlanet(activePlanet);
        userRepository.save(user);
        return toUserDTO(user);
    }

    @Override
    public List<User> getUsersWithoutSelectedPlanet() {
        return userRepository.findByActivePlanetIsNull();
    }

    @Override
    protected CrudRepository<User, Long> getRepository() {
        return userRepository;
    }
}
