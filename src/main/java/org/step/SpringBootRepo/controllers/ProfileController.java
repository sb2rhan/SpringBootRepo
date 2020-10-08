package org.step.SpringBootRepo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.step.SpringBootRepo.dtos.MessageDTO;
import org.step.SpringBootRepo.dtos.ProfileDTO;
import org.step.SpringBootRepo.entities.Profile;
import org.step.SpringBootRepo.services.CrudService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final CrudService<Profile, Long> profileCrudService;

    @Autowired
    public ProfileController(CrudService<Profile, Long> profileCrudService) {
        this.profileCrudService = profileCrudService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> findAll() {
        List<ProfileDTO> profileDTOList = profileCrudService.findAll()
                .stream()
                .map(ProfileDTO::new)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "token");

        return new ResponseEntity<>(
                profileDTOList, headers, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable(name = "id") String id) {
        final Long idP = Long.parseLong(id);
        final Profile profile = profileCrudService.findById(idP);

        return ResponseEntity.ok(new ProfileDTO(profile));
    }

    @GetMapping("/private/{id}/messages")
    public ResponseEntity<List<MessageDTO>> findProfileMessages(@PathVariable(name = "id") String id) {
        final Long idP = Long.parseLong(id);
        final Profile profile = profileCrudService.findById(idP);
        final List<MessageDTO> messages = profile.getMessages()
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messages);
    }
}
