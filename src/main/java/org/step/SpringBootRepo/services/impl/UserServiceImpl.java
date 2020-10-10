package org.step.SpringBootRepo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.step.SpringBootRepo.entities.User;
import org.step.SpringBootRepo.exceptions.UserNotFoundException;
import org.step.SpringBootRepo.repositories.UserRepository;
import org.step.SpringBootRepo.services.CrudService;
import org.step.SpringBootRepo.util.DBUtil;

import java.util.List;

@Service
public class UserServiceImpl implements CrudService<User, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DBUtil<User> userDBUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, DBUtil<User> userDBUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDBUtil = userDBUtil;
    }

    @Override
    @Transactional
    public User save(User user) {
        final String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setId(userDBUtil.generateFreeId(userRepository));
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException
                        (String.format("User with id %d was not found", id), id));
    }

    @Override
    @Transactional(readOnly = true)
    public User find(User user) {
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException
                        (String.format("User with id %d was not found", user.getId()), user.getId()));
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        userRepository.updateUsername(user.getUsername(), id);
    }
}
