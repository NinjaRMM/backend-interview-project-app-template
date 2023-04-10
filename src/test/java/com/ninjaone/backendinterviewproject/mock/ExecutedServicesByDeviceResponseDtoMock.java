package com.ninjaone.backendinterviewproject.mock;

import com.ninjaone.backendinterviewproject.dto.output.ExecutedServicesByDeviceResponseDto;

public class ExecutedServicesByDeviceResponseDtoMock implements ExecutedServicesByDeviceResponseDto {

    public ExecutedServicesByDeviceResponseDtoMock() {
    }

    @Override
    public String getServiceName() {
        return null;
    }

    @Override
    public Float getCost() {
        return null;
    }

    @Override
    public Integer getQuantity() {
        return null;
    }
}
