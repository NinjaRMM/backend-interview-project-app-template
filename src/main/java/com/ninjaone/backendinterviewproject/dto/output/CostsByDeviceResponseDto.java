package com.ninjaone.backendinterviewproject.dto.output;

import java.io.Serializable;

public interface CostsByDeviceResponseDto {

    Long getDeviceId();
    String getSystemName();
    String getDeviceType();
    Double getTotalCosts();

}
