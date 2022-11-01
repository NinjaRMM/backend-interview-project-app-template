package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;

public interface DeviceService {
    String createDevice(DeviceDto deviceDto) throws CustomValidationException;
    String updateDevice(DeviceDto deviceDto);
    DeviceDto findDeviceById(String id);
    String deleteDeviceById(String id);
}
