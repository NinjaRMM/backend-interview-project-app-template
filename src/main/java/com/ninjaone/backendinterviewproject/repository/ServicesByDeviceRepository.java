package com.ninjaone.backendinterviewproject.repository;

import com.ninjaone.backendinterviewproject.model.ServiceByDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesByDeviceRepository extends CrudRepository<ServiceByDevice, Integer> {

    @Query(value ="SELECT sd FROM ServiceByDevice sd WHERE sd.customer.id = :customerId")
    List<ServiceByDevice> findDevicesWithServicesByCustomerId(@Param("customerId") int customerId);


}
