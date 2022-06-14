package com.ninjaone.backendinterviewproject.service.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AbstractServiceInterface<T, K> {

    public T create(T entity);

    public T update(T entity);

    public void delete(T entity);

    public void deleteById(K id);

    public List<T> createAll(Iterable<T> list);

    public T findById(K id);

    public List<T> findAll();

    public List<T> findAll(Pageable pageable);

    public List<T> findAllById(Iterable<K> ids);

}
