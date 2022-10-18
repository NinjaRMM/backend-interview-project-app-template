package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.request.NewDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.request.UpdateDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.Interfaces.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private IDeviceService IDeviceService;

    public DeviceController(IDeviceService IDeviceService) {
        this.IDeviceService = IDeviceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private DeviceDTO createDevice(@RequestBody @Valid @NotNull NewDeviceRequest newDeviceRequest){
        return IDeviceService.saveDevice(newDeviceRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private DeviceDTO updateDevice(@RequestBody UpdateDeviceRequest device){
        return IDeviceService.updateDevice(device);
    }

    @GetMapping("/{id}")
    private DeviceDTO getDeviceById(@PathVariable Long id){
        return IDeviceService.getDeviceById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDevice(@PathVariable Long id){
        IDeviceService.deleteDevice(id);
    }
}
