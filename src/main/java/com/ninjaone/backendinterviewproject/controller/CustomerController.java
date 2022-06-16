package com.ninjaone.backendinterviewproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;


@RestController
@RequestMapping(path = "api/v1/admin/customer")
public class CustomerController
        extends AbstractController<Customer, String, CustomerServiceInterface> {
}
