package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.ServiceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import com.ninjaone.backendinterviewproject.service.impl.ServicesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicesServiceTest {

    @InjectMocks
    private ServicesServiceImpl servicesService;

    @Mock
    private ServiceRepository serviceRepository;

    @Spy
    private ModelMapper objectMapper;;

    private ServiceDto serviceDto;

    @BeforeEach
    void setup(){
        serviceDto = ServiceDto.builder()
                .id(2)
                .name("name")
                .build();
    }

    @Test
    void shouldCreateServiceOk() throws CustomValidationException {

        var expectedResponse = "Service created";
        when(serviceRepository.countRecordByIdAndName(anyInt(), anyString())).thenReturn(0L);
        when(serviceRepository.save(any(Service.class))).thenReturn(Service.builder().build());
        var response = servicesService.createService(serviceDto);
        assertEquals(expectedResponse, response);
    }

}
