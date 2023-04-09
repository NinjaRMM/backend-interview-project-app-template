package com.ninjaone.backendinterviewproject.dto.input;

import com.ninjaone.backendinterviewproject.model.DeviceType;

public class RmmServiceRequestDto {

    private final Long id = null;
    private final Float cost;
    private final String name;
    private final DeviceType deviceType;

    public RmmServiceRequestDto(Float cost, String name, DeviceType deviceType) {
        this.cost = cost;
        this.name = name;
        this.deviceType = deviceType;
    }

    public Long getId() {
        return id;
    }

    public Float getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }
}

