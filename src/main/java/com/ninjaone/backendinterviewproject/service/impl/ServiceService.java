package com.ninjaone.backendinterviewproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceCostServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.ServiceServiceInterface;

public class ServiceService implements ServiceServiceInterface {

    @Autowired
    ServiceNinjaOneServiceInterface serviceNinjaOneServiceInterface;

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @Autowired
    CustomerServiceCostServiceInterface customerServiceCostServiceInterface;

    @Override
    public ServiceNinjaOne addServiceToCustomer(Long serviceId, String customerId) throws GenericException {
        ServiceNinjaOne serviceNinjaOne = serviceNinjaOneServiceInterface.findById(serviceId);
        Customer customer = customerServiceInterface.findById(customerId);
        customer.getServices().add(serviceNinjaOne);
        customerServiceInterface.update(customer);
        return serviceNinjaOne;
    }

    @Override
    public void deleteServiceOfCustomer(Long serviceId, String customerId) throws GenericException {
        ServiceNinjaOne serviceNinjaOne = serviceNinjaOneServiceInterface.findById(serviceId);
        Customer customer = customerServiceInterface.findById(customerId);
        customer.getServices().remove(serviceNinjaOne);
        customerServiceInterface.update(customer);
    }

    @Override
    public List<ServiceNinjaOne> findAllServiceOfCustomer(String customerId) {
        Customer customer = customerServiceInterface.findById(customerId);
        return List.copyOf(customer.getServices());
    }

    @Override
    public double getTotalMonthlyCostOfCustomer(String customerId) {
        return customerServiceCostServiceInterface.getCostOfServicesByCustomerId(customerId);
    }

}
