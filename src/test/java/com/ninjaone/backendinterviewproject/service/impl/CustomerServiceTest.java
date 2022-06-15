package com.ninjaone.backendinterviewproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;

public class CustomerServiceTest {

    @Autowired
    private CustomerServiceInterface customerService;

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer.CustomerBuilder("23018518581").devices(new HashSet<>()).services(new HashSet<>())
                .build();
    }

    @Test
    void saveCustomerService() {
        // when(customerRepository.save(customer)).thenReturn(customer);
        String customerId = customerService.create(customer).getId();
        assertEquals(customer.getId(), customerId);
    }

}
