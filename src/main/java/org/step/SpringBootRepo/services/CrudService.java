package org.step.SpringBootRepo.services;

import java.util.List;

public interface CrudService<T, ID> {
    T save(T t);

    List<T> findAll();

    T findById(ID id);

    T find(T t);

    void delete(T t);

    void deleteById(ID id);

    void update(ID id, T t);
}
