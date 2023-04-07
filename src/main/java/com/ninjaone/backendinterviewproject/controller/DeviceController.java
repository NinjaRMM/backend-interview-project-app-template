package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.input.DeviceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public DeviceResponseDto addDevice(@RequestBody DeviceRequestDto deviceRequestDto) {
        return deviceService.create(deviceRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeviceResponseDto getDeviceById(@PathVariable Long id) {
        return deviceService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDevice(@PathVariable Long id) {
        try {
            deviceService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Device with ID " + id + " not found.");
        }
    }

}
