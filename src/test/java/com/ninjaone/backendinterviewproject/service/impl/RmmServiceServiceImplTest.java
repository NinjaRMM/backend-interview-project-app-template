package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.RmmServiceRepository;
import com.ninjaone.backendinterviewproject.dto.input.RmmServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ConflictException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.RmmService;
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
class RmmServiceServiceImplTest {

    @Mock
    private RmmServiceRepository rmmServiceRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private RmmServiceServiceImpl rmmServiceService;

    @Test
    public void createRmmService() {
        // Arrange
        RmmServiceRequestDto requestDto = new RmmServiceRequestDto(2f, "serviceName", DeviceType.MAC);
        RmmServiceResponseDto responseDto = getRmmServiceResponseDto();
        RmmService rmmService = getRmmServiceMock();

        when(mapper.map(requestDto, RmmService.class)).thenReturn(rmmService);
        when(rmmServiceRepository.save(rmmService)).thenReturn(rmmService);
        when(mapper.map(rmmService, RmmServiceResponseDto.class)).thenReturn(responseDto);

        // Act
        RmmServiceResponseDto response = rmmServiceService.create(requestDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals(response.getId(), responseDto.getId());
        verify(mapper).map(requestDto, RmmService.class);
        verify(rmmServiceRepository).save(rmmService);
        verify(rmmServiceRepository).findByNameAndDeviceType(requestDto.getName(), requestDto.getDeviceType());
        verify(mapper).map(rmmService, RmmServiceResponseDto.class);
    }

    @Test
    public void createRmmServiceWithDuplicatedRecord() {
        // Arrange
        RmmServiceRequestDto requestDto = new RmmServiceRequestDto(2f, "serviceName", DeviceType.MAC);

        when(rmmServiceRepository.findByNameAndDeviceType(requestDto.getName(), requestDto.getDeviceType()))
                .thenReturn(Optional.of(new RmmService()));

        // Act and Assert
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            rmmServiceService.create(requestDto);
        });
        assertNotNull(exception);
    }

    @Test
    public void getByIdWithSuccess() {
        // Arrange
        Long id = 1L;
        RmmServiceResponseDto responseDto = getRmmServiceResponseDto();
        RmmService rmmService = getRmmServiceMock();

        when(rmmServiceRepository.findById(id)).thenReturn(Optional.of(rmmService));
        when(mapper.map(rmmService, RmmServiceResponseDto.class)).thenReturn(responseDto);

        // Act
        RmmServiceResponseDto response = rmmServiceService.getById(id);

        // Assert
        assertEquals(rmmService.getName(), response.getName());
        verify(rmmServiceRepository).findById(id);
        verify(mapper).map(rmmService, RmmServiceResponseDto.class);
    }

    @Test
    public void getByIdWithResourceNotFound() {
        // Arrange
        Long id = 1L;
        RmmServiceResponseDto responseDto = getRmmServiceResponseDto();
        RmmService rmmService = getRmmServiceMock();

        when(rmmServiceRepository.findById(id)).thenThrow(new ResourceNotFoundException("test"));

        // Act and Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            rmmServiceService.getById(id);
        });
        assertNotNull(exception);
    }

    @Test
    public void deleteByIdWithSuccess() {
        // Arrange
        Long id = 1L;

        // Act
        rmmServiceService.deleteById(id);

        // Assert
        verify(rmmServiceRepository).deleteById(id);
    }

    private RmmService getRmmServiceMock() {
        RmmService rmmService = new RmmService();
        rmmService.setId(1L);
        rmmService.setName("test");
        rmmService.setCost(2f);
        rmmService.setDeviceType(DeviceType.MAC);
        return rmmService;
    }

    private RmmServiceResponseDto getRmmServiceResponseDto() {
        RmmServiceResponseDto response = new RmmServiceResponseDto();
        response.setId(1L);
        response.setName("test");
        response.setCost(2f);
        response.setDeviceType(DeviceType.MAC);
        return response;
    }

}