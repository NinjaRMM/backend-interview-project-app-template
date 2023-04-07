package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.input.ServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.ServiceResponseDto;

import javax.persistence.PersistenceException;

public interface ServiceService {

    ServiceResponseDto create(ServiceRequestDto serviceRequestDto);

    DeviceResponseDto getById(Long id) throws PersistenceException;

    void deleteById(Long id);

}
