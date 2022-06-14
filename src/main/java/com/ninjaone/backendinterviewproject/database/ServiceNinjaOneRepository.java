package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOneId;

@Repository
public interface ServiceNinjaOneRepository extends PagingAndSortingRepository<ServiceNinjaOne, ServiceNinjaOneId>  {
    
}
