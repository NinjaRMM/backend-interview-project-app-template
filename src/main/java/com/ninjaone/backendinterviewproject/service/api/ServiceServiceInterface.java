package com.ninjaone.backendinterviewproject.service.api;

import java.util.List;

import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;

public interface ServiceServiceInterface {
    ServiceNinjaOne addServiceToCustomer(Long serviceId, String customerId) throws GenericException;
    void deleteServiceOfCustomer(Long serviceId, String customerId) throws GenericException;
    List<ServiceNinjaOne> findAllServiceOfCustomer(String customerId);
    double getTotalMonthlyCostOfCustomer(String customerId);
}
