package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created with success"),
            @ApiResponse(code = 409, message = "Conflict, duplicated service")
    })
    public RmmServiceResponseDto addService(@RequestBody RmmServiceRequestDto rmmServiceRequestDto) {
        return rmmServiceService.create(rmmServiceRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found with success"),
            @ApiResponse(code = 404, message = "Service not found")
    })
    public RmmServiceResponseDto getServiceById(@PathVariable Long id) {
        return rmmServiceService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted with success"),
            @ApiResponse(code = 404, message = "Service not found")
    })
    public void removeRmmService(@PathVariable Long id) {
        try {
            rmmServiceService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Service with ID " + id + " not found.");
        }
    }

}
