package com.marcus.dextrachallange.framework.controller;

import com.marcus.dextrachallange.exception.HouseNotFoundException;
import com.marcus.dextrachallange.framework.service.JpaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class RestCrudController<T, Y> {
    protected abstract JpaService<T, Y> getService();

    @GetMapping
    public List<T> findAll() {
        return getService().findAll();
    }

    @PostMapping
    public T save(@Valid @RequestBody T entity) throws HouseNotFoundException {
        return getService().save(entity);
    }

    @GetMapping("{id}")
    public ResponseEntity<T> findOne(@PathVariable("id") Long id) {
        return getService().findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound().build());
    }

    @GetMapping("exists/{id}")
    public boolean exists(@PathVariable("id") Long id) {
        return getService().exists(id);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return getService().delete(id);
    }

    @DeleteMapping
    public void delete(@RequestBody T entity) {
        getService().delete(entity);
    }
}
