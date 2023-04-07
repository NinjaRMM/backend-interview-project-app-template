package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.CostsByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceExecutionResponseDto;

public interface RmmServiceExecutionService {

    RmmServiceExecutionResponseDto create(RmmServiceExecutionRequestDto rmmServiceExecutionRequestDto);

    void deleteById(Long id);

    CostsByDeviceResponseDto calculateCostsByDeviceId(Long deviceId);

}
