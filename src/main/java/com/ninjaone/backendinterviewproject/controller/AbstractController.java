package com.ninjaone.backendinterviewproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ninjaone.backendinterviewproject.service.api.AbstractServiceInterface;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AbstractController
 */

@Data
@NoArgsConstructor
public class AbstractController<T, K, S extends AbstractServiceInterface<T, K>> {

    @Autowired
    private S service;

    @GetMapping("")
    public ResponseEntity<List<T>> findAll(Pageable pageable) {
        return ResponseEntity.ok((service.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable K id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T entity) {
        return ResponseEntity.ok(service.update(entity));
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody T entity) {
        try {
            return ResponseEntity.ok(service.create(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable K id) {
        service.deleteById(id);
        return ResponseEntity.ok("Ok");
    }
}
