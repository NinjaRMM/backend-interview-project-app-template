package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.DeviceNinjaOneRepository;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.DeviceNinjaOneServiceInterface;

@Service
public class DeviceNinjaOneService extends AbstractServiceImpl<DeviceNinjaOne, Long, DeviceNinjaOneRepository>
        implements DeviceNinjaOneServiceInterface {
    public DeviceNinjaOneService(DeviceNinjaOneRepository repository) {
        super(repository);
    }
}