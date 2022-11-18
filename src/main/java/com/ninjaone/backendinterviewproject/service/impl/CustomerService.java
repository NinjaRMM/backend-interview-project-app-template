package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;

@Service
public class CustomerService extends AbstractServiceImpl<Customer, String, CustomerRepository>
        implements CustomerServiceInterface {
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
}