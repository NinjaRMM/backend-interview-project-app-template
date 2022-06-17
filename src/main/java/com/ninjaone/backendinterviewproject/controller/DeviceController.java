package com.ninjaone.backendinterviewproject.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.DeviceServiceInterface;

@RestController
@RequestMapping("/api/v1/customer/device")
public class DeviceController {

    @Autowired
    DeviceServiceInterface deviceServiceInterface;

    @PostMapping("")
    public ResponseEntity<DeviceDTO> addDeviceToCustomer(@RequestBody DeviceDTO device,
    @RequestParam String customerId) {
        try {
            DeviceNinjaOne deviceNinjaOne = deviceServiceInterface.addDeviceToCustomer(
                    new DeviceNinjaOne(device.getSystemName(), device.getOperatingSystemType(), customerId),
                    customerId);
            device.setDeviceId(deviceNinjaOne.getId());
            return new ResponseEntity<>(device, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Iterable<DeviceDTO>> findAllDeviceOfCustomer(@RequestParam String customerId) {
        try {
            List<DeviceNinjaOne> device = deviceServiceInterface.findAllDeviceOfCustomer(customerId);
            Collection<DeviceDTO> deviceDTOCollection = new ArrayList<>();
            device.forEach(deviceNinjaOne -> {
                deviceDTOCollection.add(
                        new DeviceDTO(deviceNinjaOne.getId(), deviceNinjaOne.getSystemName(),
                                deviceNinjaOne.getOperatingSystemType().getId()));
            });

            return new ResponseEntity<>(deviceDTOCollection, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Long> deleteServiceOfCustomer(@RequestParam Long deviceId) {
        try {
            deviceServiceInterface.deleteDeviceOfCustomer(deviceId);
            return new ResponseEntity<>(deviceId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
