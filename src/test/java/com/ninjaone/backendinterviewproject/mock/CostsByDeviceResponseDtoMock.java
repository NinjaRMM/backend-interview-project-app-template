package com.ninjaone.backendinterviewproject.mock;

import com.ninjaone.backendinterviewproject.dto.output.CostsByDeviceResponseDto;

public class CostsByDeviceResponseDtoMock implements CostsByDeviceResponseDto {

    public CostsByDeviceResponseDtoMock() {
    }

    @Override
    public Long getDeviceId() {
        return null;
    }

    @Override
    public String getSystemName() {
        return null;
    }

    @Override
    public String getDeviceType() {
        return null;
    }

    @Override
    public Double getTotalCosts() {
        return null;
    }
}
