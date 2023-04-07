package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.CostsByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceExecutionResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.RmmServiceExecutionService;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-execution")
public class RmmServiceExecutionController {

    private final RmmServiceExecutionService rmmServiceExecutionService;

    public RmmServiceExecutionController(RmmServiceExecutionService rmmServiceExecutionService) {
        this.rmmServiceExecutionService = rmmServiceExecutionService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RmmServiceExecutionResponseDto addService(@RequestBody RmmServiceExecutionRequestDto serviceRequestDto) {
        return rmmServiceExecutionService.create(serviceRequestDto);
    }

    @GetMapping("/calculate-by-device/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public CostsByDeviceResponseDto calculateCostsByDeviceId(@PathVariable() Long deviceId) {
        return rmmServiceExecutionService.calculateCostsByDeviceId(deviceId);
    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public RmmServiceExecutionResponseDto getServicesByDeviceType(@PathVariable String deviceType) {
//        return rmmServiceService.getByDeviceType(deviceType);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeService(@PathVariable Long id) {
        try {
            rmmServiceExecutionService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Service Execution with ID " + id + " not found.");
        }
    }

}
