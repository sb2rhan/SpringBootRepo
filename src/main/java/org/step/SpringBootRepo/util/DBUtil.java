package org.step.SpringBootRepo.util;

import org.springframework.stereotype.Component;
import org.step.SpringBootRepo.repositories.CommonRepository;

@Component
public class DBUtil<T> {

    public Long generateFreeId(CommonRepository<T> repository) {
        final long id = 1L;

        Long topId = repository.findTopByOrderByIdDesc().getId();

        if (topId == null) {
            topId = id;
        } else {
            topId = topId + 1;
        }
        return topId;
    }
}
