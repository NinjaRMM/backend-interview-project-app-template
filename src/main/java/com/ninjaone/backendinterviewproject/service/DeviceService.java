package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.input.DeviceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;

import javax.persistence.PersistenceException;

public interface DeviceService {

    DeviceResponseDto create(DeviceRequestDto deviceRequestDto);

    DeviceResponseDto getById(Long id) throws PersistenceException;

    void deleteById(Long id);

}
