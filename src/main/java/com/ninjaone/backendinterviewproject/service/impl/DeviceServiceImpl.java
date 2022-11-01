package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    public static final String DEVICE_ALREADY_EXISTS = "Device Already exists";
    public static final String DEVICE_CREATED = "Device created";
    public static final String DEVICE_NOT_FOUND = "Device not Found";
    public static final String DEVICE_UPDATED = "Device updated";
    public static final String DEVICE_REMOVED = "Device Removed";
    private final DeviceRepository deviceRepository;
    private final ModelMapper objectMapper;

    @Override
    public String createDevice(DeviceDto deviceDto) throws CustomValidationException {

        var value = deviceRepository.countRecordByIdAndSystemName(deviceDto.getId(), deviceDto.getSystemName());
        if(value > 0){
            throw new CustomValidationException(DEVICE_ALREADY_EXISTS);
        }else {
            Device device = convertToEntity(deviceDto);
            deviceRepository.save(device);
            return DEVICE_CREATED;
        }
    }

    @Override
    @Transactional
    public String updateDevice(DeviceDto deviceDto) {
        var value = deviceRepository.findById(deviceDto.getId());
        if(value.isPresent()){
            var responseEntity = value.get();
            responseEntity.setSystemName(deviceDto.getSystemName());
            responseEntity.setType(DeviceType.builder()
                            .id(deviceDto.getId())
                    .build());
            deviceRepository.save(responseEntity);
        }else{
            throw new EntityNotFoundException(DEVICE_NOT_FOUND);
        }
        return DEVICE_UPDATED;
    }

    @Override
    public DeviceDto findDeviceById(String id) {

        var value = deviceRepository.findById(Integer.parseInt(id));
        if(value.isPresent()){
            return convertToDto(value.get());
        }else {
            throw new EntityNotFoundException(DEVICE_NOT_FOUND);
        }
    }

    @Override
    public String deleteDeviceById(String id) {
        var value = deviceRepository.findById(Integer.parseInt(id));
        if(value.isPresent()){
            deviceRepository.delete(value.get());
        }else {
            throw new EntityNotFoundException(DEVICE_NOT_FOUND);
        }
        return DEVICE_REMOVED;
    }


    private DeviceDto convertToDto(Device device) {
        return objectMapper.map(device, DeviceDto.class);
    }

    private Device convertToEntity(DeviceDto deviceDto){
        return objectMapper.map(deviceDto,Device.class);
    }
}
