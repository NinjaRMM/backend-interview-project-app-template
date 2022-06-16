package com.ninjaone.backendinterviewproject.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.DeviceNinjaOneRepository;
import com.ninjaone.backendinterviewproject.exception.DeviceNinjaOneDuplicateEntityException;
import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.DeviceNinjaOneServiceInterface;

@Service
public class DeviceNinjaOneService extends AbstractServiceImpl<DeviceNinjaOne, Long, DeviceNinjaOneRepository>
                implements DeviceNinjaOneServiceInterface {

        @Override
        public DeviceNinjaOne create(DeviceNinjaOne entity) throws GenericException {

                Optional<DeviceNinjaOne> deviceNinjaOne = getRepository()
                                .findBySystemNameAndOperatingSystemTypeAndCustomer(
                                                entity.getSystemName(), entity.getOperatingSystemType(),
                                                entity.getCustomer());
                if (deviceNinjaOne.isPresent())
                        throw new DeviceNinjaOneDuplicateEntityException(
                                        "The device name: " + entity.getSystemName() + " and operating system type "
                                                        + entity.getOperatingSystemType().getId() + " and customer: "
                                                        + entity.getCustomer().getId() + "already exists");
                return super.create(entity);
        }
}