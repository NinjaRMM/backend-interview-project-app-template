package com.ninjaone.backendinterviewproject.repository;

import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {

    @Query(value ="SELECT count(e.id) FROM DEVICE e WHERE e.ID = :id OR e.SYSTEM_NAME = :systemName",  nativeQuery = true)
    Long countRecordByIdAndSystemName(@Param("id") int id, @Param("systemName") String systemName);

}
