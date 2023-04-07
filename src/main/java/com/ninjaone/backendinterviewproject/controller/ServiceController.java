package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.ServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.ServiceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponseDto addService(@RequestBody ServiceRequestDto serviceRequestDto) {
        return serviceService.create(serviceRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceResponseDto getServiceById(@PathVariable Long id) {
        return serviceService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> removeService(@PathVariable Long id) {
        try {
            serviceService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Service with ID " + id + " not found.");
        }
    }

}
