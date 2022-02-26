package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;

import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(ID id) throws NotFoundException;

    T save(T object);
}
