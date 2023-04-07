package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.RmmServiceRepository;
import com.ninjaone.backendinterviewproject.dto.input.RmmServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ConflictException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.RmmService;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class RmmServiceServiceImpl implements RmmServiceService {

    private final RmmServiceRepository rmmServiceRepository;
    private final ModelMapper mapper;

    public RmmServiceServiceImpl(RmmServiceRepository rmmServiceRepository, ModelMapper mapper) {
        this.rmmServiceRepository = rmmServiceRepository;
        this.mapper = mapper;
    }

    @Override
    public RmmServiceResponseDto create(RmmServiceRequestDto rmmServiceRequestDto) {
        boolean duplicatedRecord = rmmServiceRepository
                .findByNameAndDeviceType(rmmServiceRequestDto.getName(), rmmServiceRequestDto.getDeviceType())
                .isPresent();
        if (duplicatedRecord) throw new ConflictException("This record already exists");

        RmmService record = mapper.map(rmmServiceRequestDto, RmmService.class);
        return mapper.map(rmmServiceRepository.save(record), RmmServiceResponseDto.class);
    }

    @Override
    public RmmServiceResponseDto getById(Long id) throws PersistenceException {
        RmmService response = rmmServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RmmService with ID " + id + " not found."));
        return mapper.map(response, RmmServiceResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        rmmServiceRepository.deleteById(id);
    }

}
