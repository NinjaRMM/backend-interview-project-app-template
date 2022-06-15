package com.ninjaone.backendinterviewproject.database;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;

@Repository
public interface ServiceNinjaOneRepository extends PagingAndSortingRepository<ServiceNinjaOne, Long>  {
    Optional<ServiceNinjaOne> findByServiceNameAndOperatingSystem(String serviceName, OperatingSystem operatingSystem);
}
