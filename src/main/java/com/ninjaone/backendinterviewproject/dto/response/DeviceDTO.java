package com.ninjaone.backendinterviewproject.dto.response;

public class DeviceDTO {

    private String id;
    private String deviceName;
    private String deviceTypeId;

    public DeviceDTO() { }
    public DeviceDTO(String id, String deviceName, String deviceTypeId) {
        this.setId(id);
        this.setDeviceName(deviceName);
        this.setDeviceTypeId(deviceTypeId);
    }

    public String getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
}
