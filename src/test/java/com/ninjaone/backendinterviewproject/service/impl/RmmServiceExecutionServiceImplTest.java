package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.RmmServiceExecutionRepository;
import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.*;
import com.ninjaone.backendinterviewproject.exception.BadRequestException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.mock.CostsByDeviceResponseDtoMock;
import com.ninjaone.backendinterviewproject.mock.ExecutedServicesByDeviceResponseDtoMock;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.RmmService;
import com.ninjaone.backendinterviewproject.model.RmmServiceExecution;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RmmServiceExecutionServiceImplTest {

    private static final Long DEVICE_ID = 1L;
    private static final Long SERVICE_ID = 2L;

    @Mock
    private RmmServiceExecutionRepository rmmServiceExecutionRepository;

    @Mock
    private RmmServiceService rmmServiceService;

    @Mock
    private DeviceService deviceService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private RmmServiceExecutionServiceImpl rmmServiceExecutionService;

    @Test
    public void createRmmServiceExecution() {
        // Arrange
        RmmServiceExecutionRequestDto requestDto = new RmmServiceExecutionRequestDto(DEVICE_ID, SERVICE_ID, 3);

        RmmService rmmService = getRmmService();
        Device device = getDevice();
        RmmServiceExecution rmmServiceExecution = getRmmServiceExecution();
        RmmServiceResponseDto rmmServiceResponseDto = new RmmServiceResponseDto();
        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();

        RmmServiceExecutionResponseDto mockResponseDto = getRmmServiceExecutionResponseDto();

        when(mapper.map(requestDto, RmmServiceExecution.class)).thenReturn(rmmServiceExecution);
        when(rmmServiceService.getById(SERVICE_ID)).thenReturn(rmmServiceResponseDto);
        when(deviceService.getById(DEVICE_ID)).thenReturn(deviceResponseDto);
        when(mapper.map(rmmServiceResponseDto, RmmService.class)).thenReturn(rmmService);
        when(mapper.map(deviceResponseDto, Device.class)).thenReturn(device);
        when(rmmServiceExecutionRepository.save(rmmServiceExecution)).thenReturn(rmmServiceExecution);
        when(mapper.map(rmmServiceExecution, RmmServiceExecutionResponseDto.class)).thenReturn(mockResponseDto);

        // Act
        RmmServiceExecutionResponseDto responseDto = rmmServiceExecutionService.create(requestDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals(rmmServiceExecution.getId(), responseDto.getId());
        verify(mapper).map(requestDto, RmmServiceExecution.class);
        verify(rmmServiceService).getById(rmmServiceExecution.getRmmService().getId());
        verify(deviceService).getById(rmmServiceExecution.getDevice().getId());
        verify(rmmServiceExecutionRepository).save(rmmServiceExecution);
        verify(mapper).map(rmmServiceExecution, RmmServiceExecutionResponseDto.class);
    }

    @Test
    public void createRmmServiceExecutionWithDeviceAndServiceOfDifferentTypes() {
        // Arrange
        RmmServiceExecutionRequestDto requestDto = new RmmServiceExecutionRequestDto(DEVICE_ID, SERVICE_ID, 3);

        RmmService rmmService = getRmmService();
        Device device = getDevice();
        device.setDeviceType(DeviceType.DEBIAN_SERVER);
        RmmServiceExecution rmmServiceExecution = getRmmServiceExecution();
        RmmServiceResponseDto rmmServiceResponseDto = new RmmServiceResponseDto();
        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();

        RmmServiceExecutionResponseDto mockResponseDto = getRmmServiceExecutionResponseDto();

        when(mapper.map(requestDto, RmmServiceExecution.class)).thenReturn(rmmServiceExecution);
        when(rmmServiceService.getById(SERVICE_ID)).thenReturn(rmmServiceResponseDto);
        when(deviceService.getById(DEVICE_ID)).thenReturn(deviceResponseDto);
        when(mapper.map(rmmServiceResponseDto, RmmService.class)).thenReturn(rmmService);
        when(mapper.map(deviceResponseDto, Device.class)).thenReturn(device);

        // Act and Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            rmmServiceExecutionService.create(requestDto);
        });
        assertNotNull(exception);
    }

    @Test
    public void deleteByIdWithSuccess() {
        // Arrange
        Long id = 1L;

        // Act
        rmmServiceExecutionService.deleteById(id);

        // Assert
        verify(rmmServiceExecutionRepository).deleteById(id);
    }

    @Test
    public void calculateCostsByDeviceIdWithSuccess() {
        // Arrange
        Long deviceId = 1L;
        CostsByDeviceResponseDto costs = new CostsByDeviceResponseDtoMock();
        when(rmmServiceExecutionRepository.calculateCostsByDeviceId(deviceId)).thenReturn(Optional.of(costs));

        // Act
        CostsByDeviceResponseDto result = rmmServiceExecutionService.calculateCostsByDeviceId(deviceId);

        // Assert
        assertNotNull(result);
        assertEquals(costs, result);
    }

    @Test
    public void calculateCostsByDeviceIdWithNoCostsByDevice() {
        // Arrange
        Long deviceId = 1L;
        when(rmmServiceExecutionRepository.calculateCostsByDeviceId(deviceId)).thenReturn(Optional.empty());


        // Act and Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                rmmServiceExecutionService.calculateCostsByDeviceId(deviceId)
        );
        assertNotNull(exception);
    }

    @Test
    public void getExecutedServicesByDeviceIdWithSuccess() {
        // Arrange
        Long deviceId = 1L;
        List<ExecutedServicesByDeviceResponseDto> mockExecuted = new ArrayList<>();
        mockExecuted.add(new ExecutedServicesByDeviceResponseDtoMock());
        when(rmmServiceExecutionRepository.getExecutedServicesByDeviceId(deviceId)).thenReturn(mockExecuted);

        // Act
        List<ExecutedServicesByDeviceResponseDto> result = rmmServiceExecutionService.getExecutedServicesByDeviceId(deviceId);

        // Assert
        assertNotNull(result);
        assertEquals(mockExecuted, result);
    }

    @Test
    public void getExecutedServicesByDeviceIdWithNoExecutedServicesByDevice() {
        // Arrange
        Long deviceId = 1L;
        when(rmmServiceExecutionRepository.getExecutedServicesByDeviceId(deviceId)).thenReturn(new ArrayList<>());


        // Act and Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                rmmServiceExecutionService.getExecutedServicesByDeviceId(deviceId)
        );
        assertNotNull(exception);
    }

    private RmmService getRmmService() {
        RmmService rmmService = new RmmService();
        rmmService.setId(SERVICE_ID);
        rmmService.setDeviceType(DeviceType.MAC);
        return rmmService;
    }

    private Device getDevice() {
        Device device = new Device();
        device.setId(DEVICE_ID);
        device.setDeviceType(DeviceType.MAC);
        return device;
    }

    private RmmServiceExecution getRmmServiceExecution() {
        RmmServiceExecution rmmServiceExecution = new RmmServiceExecution();
        rmmServiceExecution.setId(1L);
        rmmServiceExecution.setRmmService(getRmmService());
        rmmServiceExecution.setDevice(getDevice());
        rmmServiceExecution.setQuantity(3);
        return rmmServiceExecution;
    }

    private RmmServiceExecutionResponseDto getRmmServiceExecutionResponseDto() {
        RmmServiceExecutionResponseDto responseDto = new RmmServiceExecutionResponseDto();
        responseDto.setId(1L);
        responseDto.setDeviceId(DEVICE_ID);
        responseDto.setRmmServiceId(SERVICE_ID);
        responseDto.setQuantity(3);
        responseDto.setExecutionDateTime(LocalDateTime.now());
        return responseDto;
    }

}