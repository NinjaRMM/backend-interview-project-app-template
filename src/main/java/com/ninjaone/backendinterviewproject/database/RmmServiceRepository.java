package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.RmmService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RmmServiceRepository extends JpaRepository<RmmService, Long> {

    Optional<RmmService> findByNameAndDeviceType(String name, DeviceType deviceType);

}
