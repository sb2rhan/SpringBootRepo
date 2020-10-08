package org.step.SpringBootRepo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.step.SpringBootRepo.dtos.UserDTO;
import org.step.SpringBootRepo.entities.User;
import org.step.SpringBootRepo.services.CrudService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CrudService<User, Long> userCrudService;

    @Autowired
    public UserController(CrudService<User, Long> userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> usersDTO = userCrudService.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "token");

        return new ResponseEntity<>(
                usersDTO, headers, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") String id) {
        final long userId = Long.parseLong(id);
        final User foundUser = userCrudService.findById(userId);
        return ResponseEntity.ok(new UserDTO(foundUser));
    }

    @DeleteMapping("/private/{id}")
    public String deleteById(@PathVariable(name = "id") String id) {
        final long userId = Long.parseLong(id);
        userCrudService.deleteById(userId);
        return String.format("User with id: %d has been deleted!", userId);
    }
}
