package com.ninjaone.backendinterviewproject.database;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.model.OperatingSystemType;

@Repository
public interface DeviceNinjaOneRepository extends PagingAndSortingRepository<DeviceNinjaOne, Long>  {
    Optional<DeviceNinjaOne> findBySystemNameAndOperatingSystemTypeAndCustomer(String serviceName, OperatingSystemType operatingSystemType, Customer customer);
}
