package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.CustomerDto;

public interface CustomerService {
    String registerCustomerWithDeviceAndServices(CustomerDto customerDto);

    String getCustomerTotalBillMonthly(String customerId);
}
