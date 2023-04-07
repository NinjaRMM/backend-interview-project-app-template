package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.input.ServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.ServiceResponseDto;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper mapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper mapper) {
        this.serviceRepository = serviceRepository;
        this.mapper = mapper;
    }

    @Override
    public ServiceResponseDto create(ServiceRequestDto serviceRequestDto) {
        return null;
    }

    @Override
    public ServiceResponseDto getById(Long id) throws PersistenceException {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

}
