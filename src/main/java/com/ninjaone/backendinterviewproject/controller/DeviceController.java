package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private  String createDevice(@RequestBody DeviceDto deviceDto) throws CustomValidationException {
        log.info("createDevice-request: " + deviceDto);
        var serviceResponse = deviceService.createDevice(deviceDto);
        log.info("createDevice-response: " + serviceResponse);
        return serviceResponse;
    }

    @PutMapping
    public ResponseEntity<String> updateDevice(@RequestBody DeviceDto deviceDto)
    {
        log.info("updateDevice-request: " + deviceDto);
        var serviceResponse = deviceService.updateDevice(deviceDto);
        log.info("updateDevice-response: " + serviceResponse);
        return ResponseEntity.ok(serviceResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> findDeviceById(@PathVariable String id) {
        var response = deviceService.findDeviceById(id);
        log.info("Response: "+response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteDeviceById(@PathVariable String id){
        log.info("deleteDeviceById-request: " + id);
        var serviceResponse = deviceService.deleteDeviceById(id);
        return  ResponseEntity.ok(serviceResponse);
    }

}
