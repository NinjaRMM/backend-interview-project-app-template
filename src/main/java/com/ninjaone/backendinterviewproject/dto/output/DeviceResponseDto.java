package com.ninjaone.backendinterviewproject.dto.output;

public class DeviceResponseDto {

    private Long id;
    private String systemName;
    private String deviceType;

    public DeviceResponseDto() {
    }

    public DeviceResponseDto(Long id, String systemName, String deviceType) {
        this.id = id;
        this.systemName = systemName;
        this.deviceType = deviceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}