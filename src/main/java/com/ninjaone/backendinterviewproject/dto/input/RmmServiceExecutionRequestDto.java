package com.ninjaone.backendinterviewproject.dto.input;

public class RmmServiceExecutionRequestDto {

    private final Long id = null;
    private final Long deviceId;
    private final Long rmmServiceId;
    private final Integer quantity;

    public RmmServiceExecutionRequestDto(Long deviceId, Long rmmServiceId, Integer quantity) {
        this.deviceId = deviceId;
        this.rmmServiceId = rmmServiceId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public Long getRmmServiceId() {
        return rmmServiceId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

