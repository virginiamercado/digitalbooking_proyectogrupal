package com.example.digitalBooking.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    T save(T t) throws Exception;
    Optional<T> findById(Long id);
    List<T> findAll();
    T update(T t);
    void delete(Long id);
}
