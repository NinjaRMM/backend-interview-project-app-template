package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.RmmServiceExecutionRepository;
import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.*;
import com.ninjaone.backendinterviewproject.exception.BadRequestException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.RmmService;
import com.ninjaone.backendinterviewproject.model.RmmServiceExecution;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.RmmServiceExecutionService;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RmmServiceExecutionServiceImpl implements RmmServiceExecutionService {

    private final RmmServiceExecutionRepository rmmServiceExecutionRepository;
    private final RmmServiceService rmmServiceService;
    private final DeviceService deviceService;
    private final ModelMapper mapper;

    public RmmServiceExecutionServiceImpl(RmmServiceExecutionRepository rmmServiceExecutionRepository, RmmServiceService rmmServiceService, DeviceService deviceService, ModelMapper mapper) {
        this.rmmServiceExecutionRepository = rmmServiceExecutionRepository;
        this.rmmServiceService = rmmServiceService;
        this.deviceService = deviceService;
        this.mapper = mapper;
    }

    @Override
    public RmmServiceExecutionResponseDto create(RmmServiceExecutionRequestDto rmmServiceExecutionRequestDto) {
        RmmServiceExecution record = mapper.map(rmmServiceExecutionRequestDto, RmmServiceExecution.class);

        RmmServiceResponseDto rmmServiceDto = rmmServiceService.getById(record.getRmmService().getId());
        record.setRmmService(mapper.map(rmmServiceDto, RmmService.class));

        DeviceResponseDto deviceDto = deviceService.getById(record.getDevice().getId());
        record.setDevice(mapper.map(deviceDto, Device.class));

        boolean deviceAndServiceOfSameType = record.getDevice().getDeviceType().equals(record.getRmmService().getDeviceType());
        if (!deviceAndServiceOfSameType) throw new BadRequestException("Device and Service of different types");

        return mapper.map(rmmServiceExecutionRepository.save(record), RmmServiceExecutionResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        rmmServiceExecutionRepository.deleteById(id);
    }

    @Override
    public CostsByDeviceResponseDto calculateCostsByDeviceId(Long deviceId) {
        return rmmServiceExecutionRepository.calculateCostsByDeviceId(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("No costs found for this device"));
    }

    @Override
    public List<ExecutedServicesByDeviceResponseDto> getExecutedServicesByDeviceId(Long deviceId) {
        List<ExecutedServicesByDeviceResponseDto> responseDtoList = rmmServiceExecutionRepository.getExecutedServicesByDeviceId(deviceId);
        if (responseDtoList.isEmpty()) {
            throw new ResourceNotFoundException("No executed services found for this device");
        }
        return responseDtoList;
    }

}
