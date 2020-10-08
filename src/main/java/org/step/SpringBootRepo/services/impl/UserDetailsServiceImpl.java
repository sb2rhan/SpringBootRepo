package org.step.SpringBootRepo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.step.SpringBootRepo.config.UserDetailsImpl;
import org.step.SpringBootRepo.entities.User;
import org.step.SpringBootRepo.repositories.UserRepository;

/*
 * This implementation of UserDetailsService is the logic of UserDetailImpl
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> {
            throw new RuntimeException(String.format("User: %s is not found", username));
        });

        return new UserDetailsImpl(user);
    }
}
