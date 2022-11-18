package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.OperatingSystemType;

@Repository
public interface OperatingSystemTypeRepository extends PagingAndSortingRepository<OperatingSystemType, String>  {
    
}
