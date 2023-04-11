package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.CostsByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.ExecutedServicesByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceExecutionResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.RmmServiceExecutionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-execution")
public class RmmServiceExecutionController {

    private final RmmServiceExecutionService rmmServiceExecutionService;

    public RmmServiceExecutionController(RmmServiceExecutionService rmmServiceExecutionService) {
        this.rmmServiceExecutionService = rmmServiceExecutionService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "calculateValuesByDevice", allEntries = true)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Device and RmmService of different DeviceType"),
            @ApiResponse(code = 404, message = "No Device found or no RmmService found")
    })
    public RmmServiceExecutionResponseDto addService(@RequestBody RmmServiceExecutionRequestDto serviceRequestDto) {
        return rmmServiceExecutionService.create(serviceRequestDto);
    }

    @GetMapping("/calculate-by-device/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "calculateValuesByDevice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "No Costs found for this device")
    })
    public CostsByDeviceResponseDto calculateCostsByDeviceId(@PathVariable() Long deviceId) {
        return rmmServiceExecutionService.calculateCostsByDeviceId(deviceId);
    }

    @GetMapping("/executed-by-device/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "No Executed Services found for this device")
    })
    public List<ExecutedServicesByDeviceResponseDto> getExecutedServicesByDeviceId(@PathVariable() Long deviceId) {
        return rmmServiceExecutionService.getExecutedServicesByDeviceId(deviceId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "calculateValuesByDevice", allEntries = true)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted with success"),
            @ApiResponse(code = 404, message = "Service Execution not found")
    })
    public void removeServiceExecution(@PathVariable Long id) {
        try {
            rmmServiceExecutionService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Service Execution with ID " + id + " not found.");
        }
    }

}
