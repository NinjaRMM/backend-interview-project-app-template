package com.ninjaone.backendinterviewproject.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.dto.ServiceDTO;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.ServiceServiceInterface;

@RestController
@RequestMapping("/api/v1/customer/service")
public class ServiceController {

    @Autowired
    ServiceServiceInterface serviceServiceInterface;

    @PostMapping("/{id}")
    public ResponseEntity<ServiceDTO> addServiceToCustomer(@PathVariable Long serviceId,
            @PathVariable String customerId) {
        try {
            ServiceNinjaOne serviceNinjaOne = serviceServiceInterface.addServiceToCustomer(serviceId, customerId);
            ServiceDTO serviceDTO = new ServiceDTO(serviceNinjaOne.getId(), serviceNinjaOne.getServiceName(),
                    serviceNinjaOne.getServicePrice());
            return new ResponseEntity<>(serviceDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ServiceDTO>> findAllServiceOfCustomer(@PathVariable String customerId) {
        try {
            List<ServiceNinjaOne> services = serviceServiceInterface.findAllServiceOfCustomer(customerId);
            Collection<ServiceDTO> serviceDTOCollection = new ArrayList<>();
            services.forEach(serviceNinjaOne -> 
                serviceDTOCollection.add(
                        new ServiceDTO(serviceNinjaOne.getId(), serviceNinjaOne.getServiceName(),
                                serviceNinjaOne.getServicePrice()))
            );

            return new ResponseEntity<>(serviceDTOCollection, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteServiceOfCustomer(@PathVariable Long serviceId,
            @PathVariable String customerId) {
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
