package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.dto.input.DeviceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ConflictException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelMapper mapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, ModelMapper mapper) {
        this.deviceRepository = deviceRepository;
        this.mapper = mapper;
    }

    @Override
    public DeviceResponseDto create(DeviceRequestDto deviceRequestDto) {
        boolean duplicatedRecord = deviceRepository
                .findBySystemNameAndDeviceType(deviceRequestDto.getSystemName(), deviceRequestDto.getDeviceType())
                .isPresent();
        if (duplicatedRecord) throw new ConflictException("This record already exists");

        Device record = mapper.map(deviceRequestDto, Device.class);
        return mapper.map(deviceRepository.save(record), DeviceResponseDto.class);
    }

    @Override
    public DeviceResponseDto getById(Long id) throws PersistenceException {
        Device response = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device with ID " + id + " not found."));
        return mapper.map(response, DeviceResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }
}
