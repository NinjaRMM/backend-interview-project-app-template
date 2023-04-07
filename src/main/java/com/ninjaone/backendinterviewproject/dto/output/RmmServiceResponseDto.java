package com.ninjaone.backendinterviewproject.dto.output;

import com.ninjaone.backendinterviewproject.model.DeviceType;

public class RmmServiceResponseDto {

    private Long id;
    private Float cost;
    private String name;
    private DeviceType deviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}