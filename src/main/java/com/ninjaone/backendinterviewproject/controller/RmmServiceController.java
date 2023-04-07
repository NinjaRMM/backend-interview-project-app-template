package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class RmmServiceController {

    private final RmmServiceService rmmServiceService;

    public RmmServiceController(RmmServiceService rmmServiceService) {
        this.rmmServiceService = rmmServiceService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RmmServiceResponseDto addService(@RequestBody RmmServiceRequestDto rmmServiceRequestDto) {
        return rmmServiceService.create(rmmServiceRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RmmServiceResponseDto getServiceById(@PathVariable Long id) {
        return rmmServiceService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRmmService(@PathVariable Long id) {
        try {
            rmmServiceService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Service with ID " + id + " not found.");
        }
    }

}
