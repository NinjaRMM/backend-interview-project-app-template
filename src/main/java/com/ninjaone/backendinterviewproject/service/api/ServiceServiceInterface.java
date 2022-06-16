package com.ninjaone.backendinterviewproject.service.api;

import java.util.List;

import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;

public interface ServiceServiceInterface {
    ServiceNinjaOne addServiceToCustomer(Long serviceId, String customerId);
    void deleteServiceOfCustomer(Long serviceId, String customerId);
    List<ServiceNinjaOne> findAllServiceOfCustomer(String customerId);
    double getTotalMonthlyCostOfCustomer(String customerId);
}
