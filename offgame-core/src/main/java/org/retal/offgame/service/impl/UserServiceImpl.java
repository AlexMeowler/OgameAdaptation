package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.User;
import org.retal.offgame.repository.UserRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    protected CrudRepository<User, Long> getRepository() {
        return userRepository;
    }
}
