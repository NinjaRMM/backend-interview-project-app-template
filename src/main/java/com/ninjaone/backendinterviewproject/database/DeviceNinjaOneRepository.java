package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;

@Repository
public interface DeviceNinjaOneRepository extends PagingAndSortingRepository<DeviceNinjaOne, Long>  {
    
}
