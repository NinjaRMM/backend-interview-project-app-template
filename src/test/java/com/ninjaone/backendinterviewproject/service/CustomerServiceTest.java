package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.CustomerDto;
import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.dto.DeviceTypeDto;
import com.ninjaone.backendinterviewproject.dto.ServiceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.model.*;
import com.ninjaone.backendinterviewproject.repository.CustomerRepository;
import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import com.ninjaone.backendinterviewproject.repository.ServicesByDeviceRepository;
import com.ninjaone.backendinterviewproject.service.impl.CustomerServiceImpl;
import com.ninjaone.backendinterviewproject.service.impl.ServicesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ServicesByDeviceRepository servicesByDeviceRepository;
    private CustomerDto customerDto;
    private List<ServiceByDevice> serviceByDeviceList;

    @BeforeEach
    void setup(){
        customerDto = CustomerDto.builder()
                .id(2)
                .name("name cudtomer")
                .devices(Collections.singletonList(DeviceDto.builder()
                        .type(DeviceTypeDto.builder()
                                .id(2)
                                .build())
                        .systemName("Name")
                        .services(Collections.singletonList(ServiceDto.builder()
                                        .name("name")
                                        .id(3)
                                .build()))
                        .build()))
                .build();

        serviceByDeviceList = Collections.singletonList(ServiceByDevice.builder()
                .customer(Customer.builder()
                        .id(3)
                        .build())
                .service(Service.builder()
                        .id(1)
                        .cost(4)
                        .build())
                .device(Device.builder()
                        .id(1)
                        .type(DeviceType.builder()
                                .id(2)
                                .build())
                        .build())
                .build());
    }

    @Test
    void shouldCreateServiceOk() {

        var expectedResponse = "Process Ok";
        when(customerRepository.save(any(Customer.class))).thenReturn(Customer.builder().build());
        when(servicesByDeviceRepository.save(any(ServiceByDevice.class))).thenReturn(ServiceByDevice.builder().build());
        var response = customerService.registerCustomerWithDeviceAndServices(customerDto);
        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldGetCustomerTotalBillMonthlyOk() {

        var expectedResponse = "Total monthly = 4.0";
        when(servicesByDeviceRepository.findDevicesWithServicesByCustomerId(anyInt())).thenReturn(serviceByDeviceList);
        var response = customerService.getCustomerTotalBillMonthly("3");
        assertEquals(expectedResponse, response);
    }

}
