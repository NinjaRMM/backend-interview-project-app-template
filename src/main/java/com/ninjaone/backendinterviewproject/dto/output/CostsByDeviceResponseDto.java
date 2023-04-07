package com.ninjaone.backendinterviewproject.dto.output;

public interface CostsByDeviceResponseDto {

    Long getDeviceId();

    String getSystemName();

    String getDeviceType();

    Double getTotalCosts();

}
