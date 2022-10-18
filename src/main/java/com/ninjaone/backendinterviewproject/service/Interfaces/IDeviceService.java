package com.ninjaone.backendinterviewproject.service.Interfaces;

import com.ninjaone.backendinterviewproject.dto.request.NewDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.request.UpdateDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;

public interface IDeviceService {

    DeviceDTO saveDevice(NewDeviceRequest newDeviceRequest);

    DeviceDTO updateDevice(UpdateDeviceRequest updateDeviceRequest);

    DeviceDTO getDeviceById(Long id);

    void deleteDevice(Long id);
}
