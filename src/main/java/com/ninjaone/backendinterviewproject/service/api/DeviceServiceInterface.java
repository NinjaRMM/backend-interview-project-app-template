package com.ninjaone.backendinterviewproject.service.api;

import java.util.List;

import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;

public interface DeviceServiceInterface {
    DeviceNinjaOne addDeviceToCustomer(DeviceNinjaOne deviceNinjaOne, String customerId) throws GenericException;
    void deleteDeviceOfCustomer(Long deviceId);
    List<DeviceNinjaOne> findAllDeviceOfCustomer(String customerId);
}
