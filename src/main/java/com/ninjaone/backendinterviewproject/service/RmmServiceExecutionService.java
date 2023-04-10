package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.CostsByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.ExecutedServicesByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceExecutionResponseDto;

import java.util.List;

public interface RmmServiceExecutionService {

    RmmServiceExecutionResponseDto create(RmmServiceExecutionRequestDto rmmServiceExecutionRequestDto);

    void deleteById(Long id);

    CostsByDeviceResponseDto calculateCostsByDeviceId(Long deviceId);

    List<ExecutedServicesByDeviceResponseDto> getExecutedServicesByDeviceId(Long deviceId);

}
