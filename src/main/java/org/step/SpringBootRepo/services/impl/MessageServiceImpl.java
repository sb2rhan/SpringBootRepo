package org.step.SpringBootRepo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.step.SpringBootRepo.entities.Message;
import org.step.SpringBootRepo.repositories.MessageRepository;
import org.step.SpringBootRepo.services.CrudService;

import java.util.List;

@Service
public class MessageServiceImpl implements CrudService<Message, Long> {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Message find(Message message) {
        return messageRepository.findById(message.getId()).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long aLong, Message message) {
    }
}
