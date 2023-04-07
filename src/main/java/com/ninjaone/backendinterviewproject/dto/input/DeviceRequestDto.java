package com.ninjaone.backendinterviewproject.dto.input;

import com.ninjaone.backendinterviewproject.model.DeviceType;

public class DeviceRequestDto {

    private String systemName;
    private DeviceType deviceType;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}

