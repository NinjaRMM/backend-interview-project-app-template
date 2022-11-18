package com.ninjaone.backendinterviewproject.service.api;

@FunctionalInterface
public interface CustomerServiceCostServiceInterface {
    double getCostOfServicesByCustomerId(String customerId);
}
