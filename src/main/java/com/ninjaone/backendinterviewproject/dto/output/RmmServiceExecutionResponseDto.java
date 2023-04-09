package com.ninjaone.backendinterviewproject.dto.output;

import java.time.LocalDateTime;

public class RmmServiceExecutionResponseDto {

    private Long id;
    private Long deviceId;
    private Long rmmServiceId;
    private Integer quantity;
    private LocalDateTime executionDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getRmmServiceId() {
        return rmmServiceId;
    }

    public void setRmmServiceId(Long rmmServiceId) {
        this.rmmServiceId = rmmServiceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }
}