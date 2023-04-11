package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.DeviceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created with success"),
            @ApiResponse(code = 409, message = "Conflict, duplicated device")
    })
    public DeviceResponseDto addDevice(@RequestBody DeviceRequestDto deviceRequestDto) {
        return deviceService.create(deviceRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found with success"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    public DeviceResponseDto getDeviceById(@PathVariable Long id) {
        return deviceService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted with success"),
            @ApiResponse(code = 404, message = "Device not found")
    })
    public void removeDevice(@PathVariable Long id) {
        try {
            deviceService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Device with ID " + id + " not found.");
        }
    }

}
