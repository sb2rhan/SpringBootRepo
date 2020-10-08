package org.step.SpringBootRepo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.step.SpringBootRepo.dtos.IdProjection;

@NoRepositoryBean
public interface CommonRepository<T> extends JpaRepository<T, Long> {
    IdProjection findTopByOrderByIdDesc();
}
