package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.dto.DeviceTypeDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.service.impl.DeviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @Spy
    private ModelMapper objectMapper;;

    private DeviceDto deviceDto;

    @BeforeEach
    void setup(){
        deviceDto = DeviceDto.builder()
                .id(2)
                .systemName("X system")
                .type(DeviceTypeDto.builder()
                        .id(1)
                        .type("X type")
                        .build())
                .build();
    }

    @Test
    void shouldCreateDeviceOk() throws CustomValidationException {

        var expectedResponse = "Device created";
        when(deviceRepository.countRecordByIdAndSystemName(anyInt(), anyString())).thenReturn(0L);
        when(deviceRepository.save(any(Device.class))).thenReturn(Device.builder().build());
        var response = deviceService.createDevice(deviceDto);
        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldFindDeviceByIdOk() {
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(Device.builder()
                        .type(DeviceType.builder()
                                .id(1)
                                .build())
                        .id(2)
                .build()));
        var response = deviceService.findDeviceById("2");
        assertNotNull(response.getId());
    }

    @Test
    void shouldUpdateDeviceOk() {

        var expectedResponse = "Device updated";
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(Device.builder()
                        .id(3)
                        .type(DeviceType.builder()
                                .id(1)
                                .build())
                .build()));
        when(deviceRepository.save(any(Device.class))).thenReturn(Device.builder().build());
        var response = deviceService.updateDevice(deviceDto);
        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldDeleteDeviceByIdOk() {

        var expectedResponse = "Device Removed";
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(Device.builder()
                .id(3)
                .type(DeviceType.builder()
                        .id(1)
                        .build())
                .build()));
        var response = deviceService.deleteDeviceById("2");
        assertEquals(expectedResponse, response);
    }

}
