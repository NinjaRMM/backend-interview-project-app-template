package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.ServiceId;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;

@Repository
public interface ServiceNinjaOneRepository extends PagingAndSortingRepository<ServiceNinjaOne, ServiceId>  {
    
}
