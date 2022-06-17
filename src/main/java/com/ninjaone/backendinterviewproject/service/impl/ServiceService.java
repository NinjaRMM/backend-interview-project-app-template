package com.ninjaone.backendinterviewproject.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceCostServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.ServiceServiceInterface;

@Service
public class ServiceService implements ServiceServiceInterface {

    @Autowired
    ServiceNinjaOneServiceInterface serviceNinjaOneServiceInterface;

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @Autowired
    CustomerServiceCostServiceInterface customerServiceCostServiceInterface;

    @Override
    public ServiceNinjaOne addServiceToCustomer(Long serviceId, String customerId) {
        ServiceNinjaOne serviceNinjaOne = serviceNinjaOneServiceInterface.findById(serviceId);
        Customer customer = customerServiceInterface.findById(customerId);
        customer.getServices().add(serviceNinjaOne);
        customerServiceInterface.update(customer);
        return serviceNinjaOne;
    }

    @Override
    public void deleteServiceOfCustomer(Long serviceId, String customerId) {
        ServiceNinjaOne serviceNinjaOne = serviceNinjaOneServiceInterface.findById(serviceId);
        Customer customer = customerServiceInterface.findById(customerId);
        customer.getServices().remove(serviceNinjaOne);
        customerServiceInterface.update(customer);
    }

    @Override
    public Set<ServiceNinjaOne> findAllServiceOfCustomer(String customerId) {
        Customer customer = customerServiceInterface.findById(customerId);
        return customer.getServices();
    }

    @Override
    public double getTotalMonthlyCostOfCustomer(String customerId) {
        return customerServiceCostServiceInterface.getCostOfServicesByCustomerId(customerId);
    }

}
