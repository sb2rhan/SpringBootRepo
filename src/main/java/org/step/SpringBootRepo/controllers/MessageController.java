package org.step.SpringBootRepo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.step.SpringBootRepo.dtos.MessageDTO;
import org.step.SpringBootRepo.entities.Message;
import org.step.SpringBootRepo.services.CrudService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final CrudService<Message, Long> messageCrudService;

    @Autowired
    public MessageController(CrudService<Message, Long> messageCrudService) {
        this.messageCrudService = messageCrudService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> findAll() {
        final List<MessageDTO> messageDTOS = messageCrudService.findAll()
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "token");

        return new ResponseEntity<>(
                messageDTOS, headers, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable(name = "id") String id) {
        Long messageId = Long.parseLong(id);
        final Message found = messageCrudService.findById(messageId);

        return ResponseEntity.ok(new MessageDTO(found));
    }
}
