package org.step.SpringBootRepo.repositories;

import org.springframework.stereotype.Repository;
import org.step.SpringBootRepo.entities.Message;

@Repository
public interface MessageRepository extends CommonRepository<Message> {
}
