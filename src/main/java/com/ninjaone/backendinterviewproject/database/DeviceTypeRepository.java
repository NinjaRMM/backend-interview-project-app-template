package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.DeviceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeRepository extends CrudRepository<DeviceType, String> {}
