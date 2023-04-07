package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.input.RmmServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceResponseDto;

import javax.persistence.PersistenceException;

public interface RmmServiceService {

    RmmServiceResponseDto create(RmmServiceRequestDto rmmServiceRequestDto);

    RmmServiceResponseDto getById(Long id) throws PersistenceException;

    void deleteById(Long id);

}
