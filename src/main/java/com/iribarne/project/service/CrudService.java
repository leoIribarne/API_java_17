package com.iribarne.project.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    // Create
    T save(T entity);
    
    // Read
    Optional<T> findById(Long id);
    List<T> findAll();
    
    // Update
    T update(Long id, T entity);
    
    // Delete
    void delete(Long id);
    void deleteAll();
}
