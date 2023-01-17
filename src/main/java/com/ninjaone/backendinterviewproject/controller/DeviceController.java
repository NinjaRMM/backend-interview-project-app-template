package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.exceptions.DeviceNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotAvailableForDeviceException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotFoundException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ITService;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device postDeviceEntity(@RequestBody Device device){
        return deviceService.saveDeviceEntity(device);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteDeviceByID(@PathVariable Long id){
        deviceService.deleteDeviceEntityById(id);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Device getDeviceById(@PathVariable Long id) throws DeviceNotFoundException {
        return deviceService.getDeviceById(id);
    }


    @PostMapping(path = "/{id}/add-service/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    private Device addServiceToDevice(@PathVariable Long id,@PathVariable Long serviceId) throws ServiceNotAvailableForDeviceException, DeviceNotFoundException, ServiceNotFoundException {
        return deviceService.addServiceToDevice(id, serviceId);
    }

}
