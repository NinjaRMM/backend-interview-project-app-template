package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.dto.input.DeviceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ConflictException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    public void createDevice() {
        // Arrange
        DeviceRequestDto requestDto = new DeviceRequestDto("systemName", DeviceType.MAC);
        DeviceResponseDto responseDto = getDeviceResponseDto();
        Device device = getDeviceMock();

        when(mapper.map(requestDto, Device.class)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);
        when(mapper.map(device, DeviceResponseDto.class)).thenReturn(responseDto);

        // Act
        DeviceResponseDto response = deviceService.create(requestDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals(response.getId(), responseDto.getId());
        verify(mapper).map(requestDto, Device.class);
        verify(deviceRepository).save(device);
        verify(deviceRepository).findBySystemNameAndDeviceType(requestDto.getSystemName(), requestDto.getDeviceType());
        verify(mapper).map(device, DeviceResponseDto.class);
    }

    @Test
    public void createDeviceWithDuplicatedRecord() {
        // Arrange
        DeviceRequestDto requestDto = new DeviceRequestDto("systemName", DeviceType.MAC);

        when(deviceRepository.findBySystemNameAndDeviceType(requestDto.getSystemName(), requestDto.getDeviceType()))
                .thenReturn(Optional.of(new Device()));

        // Act and Assert
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            deviceService.create(requestDto);
        });
        assertNotNull(exception);
    }

    @Test
    public void getByIdWithSuccess() {
        // Arrange
        Long id = 1L;
        DeviceResponseDto responseDto = getDeviceResponseDto();
        Device device = getDeviceMock();

        when(deviceRepository.findById(id)).thenReturn(Optional.of(device));
        when(mapper.map(device, DeviceResponseDto.class)).thenReturn(responseDto);

        // Act
        DeviceResponseDto response = deviceService.getById(id);

        // Assert
        assertEquals(device.getSystemName(), response.getSystemName());
        verify(deviceRepository).findById(id);
        verify(mapper).map(device, DeviceResponseDto.class);
    }

    @Test
    public void getByIdWithResourceNotFound() {
        // Arrange
        Long id = 1L;
        DeviceResponseDto responseDto = getDeviceResponseDto();
        Device device = getDeviceMock();

        when(deviceRepository.findById(id)).thenThrow(new ResourceNotFoundException("test"));

        // Act and Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deviceService.getById(id);
        });
        assertNotNull(exception);
    }

    @Test
    public void deleteByIdWithSuccess() {
        // Arrange
        Long id = 1L;

        // Act
        deviceService.deleteById(id);

        // Assert
        verify(deviceRepository).deleteById(id);
    }

    private Device getDeviceMock() {
        Device device = new Device();
        device.setId(1L);
        device.setSystemName("systemName");
        device.setDeviceType(DeviceType.MAC);
        return device;
    }

    private DeviceResponseDto getDeviceResponseDto() {
        DeviceResponseDto response = new DeviceResponseDto();
        response.setId(1L);
        response.setSystemName("systemName");
        response.setDeviceType(DeviceType.MAC.name());
        return response;
    }

}