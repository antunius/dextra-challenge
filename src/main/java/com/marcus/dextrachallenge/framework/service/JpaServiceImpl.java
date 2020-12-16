package com.marcus.dextrachallenge.framework.service;

import com.marcus.dextrachallenge.exception.HouseNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class JpaServiceImpl<T, Y> implements JpaService<T, Y> {
    protected abstract JpaRepository<T, Long> getData();

    @Override
    public List<T> findAll() {
        return getData().findAll();
    }

    @Override
    public <S extends T> S save(S entity) throws HouseNotFoundException {
        preSave(entity);
        return getData().save(entity);
    }

    @Override
    public <S extends T> void preSave(S entity) throws HouseNotFoundException {

    }

    @Override
    public Optional<T> findById(Long id) {
        return getData().findById(id);
    }

    @Override
    public boolean exists(Long id) {
        return getData().findById(id).isPresent();
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<T> optionalT = findById(id);
            optionalT.ifPresent(getData()::delete);
            return true;
        } catch (RuntimeException runtimeException) {
            return false;
        }
    }

    @Override
    public void delete(T entity) {
        getData().delete(entity);
    }
}
