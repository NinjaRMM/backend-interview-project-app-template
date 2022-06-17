package com.ninjaone.backendinterviewproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceCostServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.DeviceNinjaOneServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.DeviceServiceInterface;

public class DeviceService implements DeviceServiceInterface {

    @Autowired
    DeviceNinjaOneServiceInterface deviceNinjaOneServiceInterface;

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @Autowired
    CustomerServiceCostServiceInterface customerServiceCostServiceInterface;

    @Override
    public DeviceNinjaOne addDeviceToCustomer(DeviceNinjaOne deviceNinjaOne, String customerId) throws GenericException {
        Customer customer = customerServiceInterface.findById(customerId);
        deviceNinjaOne.setCustomer(customer);
        deviceNinjaOneServiceInterface.create(deviceNinjaOne);
        return deviceNinjaOne;
    }

    @Override
    public void deleteDeviceOfCustomer(Long deviceId) {
        deviceNinjaOneServiceInterface.deleteById(deviceId);
    }


    @Override
    public List<DeviceNinjaOne> findAllDeviceOfCustomer(String customerId) {
        Customer customer = customerServiceInterface.findById(customerId);
        return List.copyOf(customer.getDevices());
    }

}
