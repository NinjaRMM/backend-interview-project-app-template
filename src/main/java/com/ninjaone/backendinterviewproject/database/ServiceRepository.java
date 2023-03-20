package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {}
