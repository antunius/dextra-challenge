package com.marcus.dextrachallenge.framework.service;

import com.marcus.dextrachallenge.exception.HouseNotFoundException;

import java.util.List;
import java.util.Optional;

public interface JpaService<T, Y> {
    List<T> findAll();

    <S extends T> S save(S entity) throws HouseNotFoundException;

    <S extends T> void preSave(S entity) throws HouseNotFoundException;

    Optional<T> findById(Long id);

    boolean exists(Long id);

    boolean delete(Long id);

    void delete(T entity);
}
