package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.CustomerDto;
import com.ninjaone.backendinterviewproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    private  ResponseEntity<String> createCustomerWithDevices(@RequestBody CustomerDto customerDto) {
        log.info("createCustomerWithDevices-request: " + customerDto);
        var serviceResponse = customerService.registerCustomerWithDeviceAndServices(customerDto);
        log.info("createCustomerWithDevices-response: " + serviceResponse);
        return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
    }

    @GetMapping("/{customerId}")
    private  ResponseEntity<String> getCustomerTotalBillMonthly (@PathVariable String customerId) {
        log.info("getCustomerTotalBillMonthly-request: " + customerId);
        var serviceResponse = customerService.getCustomerTotalBillMonthly(customerId);
        log.info("getCustomerTotalBillMonthly-response: " + serviceResponse);
        return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
    }


}
