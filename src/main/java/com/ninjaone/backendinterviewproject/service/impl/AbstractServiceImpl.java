package com.ninjaone.backendinterviewproject.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.AbstractEntity;
import com.ninjaone.backendinterviewproject.service.api.AbstractServiceInterface;

import lombok.Getter;

@Service
@Getter
public abstract class AbstractServiceImpl<T extends AbstractEntity<K>, K extends Serializable, R extends PagingAndSortingRepository<T, K>>
        implements AbstractServiceInterface<T, K> {
            
    @Autowired
    R repository;

    @Override
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T update(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(K id) {
        getRepository().deleteById(id);
    }

    @Override
    public List<T> createAll(Iterable<T> list) {
        List<T> returnList = new ArrayList<>();
        getRepository().saveAll(list).forEach(returnList::add);
        return returnList;
    }

    @Override
    public T findById(K id) {
        Optional<T> obj = getRepository().findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> returnList = new ArrayList<>();
        getRepository().findAll().forEach(returnList::add);
        return returnList;
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        Page<T> pagedResult = getRepository().findAll(pageable);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<T> findAllById(Iterable<K> ids) {
        List<T> returnList = new ArrayList<>();
        getRepository().findAllById(ids).forEach(returnList::add);
        return returnList;
    }
}
