package com.ninjaone.backendinterviewproject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.ServiceServiceInterface;

@RestController
@RequestMapping("/api/v1/customer/service")
public class ServiceController {

    @Autowired
    ServiceServiceInterface serviceServiceInterface;

    @PostMapping("")
    public ResponseEntity<ServiceNinjaOne> addServiceToCustomer(@RequestParam Long serviceId,
            @RequestParam String customerId) {
        try {
            ServiceNinjaOne serviceNinjaOne = serviceServiceInterface.addServiceToCustomer(serviceId, customerId);
            return new ResponseEntity<>(serviceNinjaOne, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ServiceNinjaOne>> findAllServiceOfCustomer(@RequestParam String customerId) {
        try {
            Set<ServiceNinjaOne> services = serviceServiceInterface.findAllServiceOfCustomer(customerId);
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Long> deleteServiceOfCustomer(@RequestParam Long serviceId,
            @RequestParam String customerId) {
        try {
            serviceServiceInterface.deleteServiceOfCustomer(serviceId, customerId);
            return new ResponseEntity<>(serviceId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cost/{customerId}")
    public ResponseEntity<Object> getTotalMonthlyCostOfCustomer(@PathVariable String customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceServiceInterface.getTotalMonthlyCostOfCustomer(customerId));
    }

}
