package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.interfaces.OperatingSystemIdInterface;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceCostServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;

@Service
public class CustomerServiceCostService implements CustomerServiceCostServiceInterface {

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    static final String APPLY_OF_ALL_OPERATING_SYSTEM = "GENERIC";

    @Override
    public double getCostOfServicesByCustomerId(String customerId) {
        Customer customer = customerServiceInterface.findById(customerId);
        double totalCost = 0;
        totalCost = customer.getServices().stream().mapToDouble(service -> {
             long cantDevicesFilterApply = customer.getDevices().stream().filter(device -> ifApplyOperatingSystemByServiceAndDevice(service,device)).count();
             return service.getServicePrice() * cantDevicesFilterApply;
        }).sum();
        return totalCost;
    }

    private boolean ifApplyOperatingSystemByServiceAndDevice(OperatingSystemIdInterface service, OperatingSystemIdInterface device) {
        if (service.getOperatingSystemId().equals(APPLY_OF_ALL_OPERATING_SYSTEM))
            return true;

        return device
                .getOperatingSystemId().equals(service.getOperatingSystemId());
    }

}
