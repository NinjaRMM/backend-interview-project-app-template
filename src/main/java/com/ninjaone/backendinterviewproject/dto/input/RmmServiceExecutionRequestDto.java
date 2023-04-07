package com.ninjaone.backendinterviewproject.dto.input;

public class RmmServiceExecutionRequestDto {

    private final Integer id = null;
    private final Integer deviceId;
    private final Integer rmmServiceId;
    private final Integer quantity;

    public RmmServiceExecutionRequestDto(Integer deviceId, Integer rmmServiceId, Integer quantity) {
        this.deviceId = deviceId;
        this.rmmServiceId = rmmServiceId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public Integer getRmmServiceId() {
        return rmmServiceId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

