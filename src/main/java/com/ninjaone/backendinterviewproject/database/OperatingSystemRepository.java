package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.OperatingSystem;

@Repository
public interface OperatingSystemRepository extends PagingAndSortingRepository<OperatingSystem, String>  {
    
}
