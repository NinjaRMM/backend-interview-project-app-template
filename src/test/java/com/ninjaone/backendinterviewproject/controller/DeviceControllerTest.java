package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.cache.DeviceCostCache;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServicesService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(DeviceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class DeviceControllerTest {

    public static final String deviceId = "1";
    public static final String serviceId = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    @MockBean
    private ServicesService servicesService;

    @MockBean
    private DeviceCostCache deviceCostCache;

    private Device deviceEntity;
    private Service serviceEntity;

    @BeforeEach
    void setup(){
        deviceEntity = new Device(deviceId, "Test machine","Macbook pro");
        Map<Service.OSType, Double> prices = new HashMap<>();
        prices.put(Service.OSType.MAC, 7.0);
        prices.put(Service.OSType.WINDOWS, 5.0);
        prices.put(Service.OSType.DEFAULT, 6.0);
        serviceEntity = new Service(serviceId,"TEST SERVICE",prices);
    }

    @Test
    void getDevice() throws Exception {
        when(deviceService.getDeviceEntity(deviceId)).thenReturn(Optional.of(deviceEntity));
        mockMvc.perform(get("/device/" + deviceId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(Map.of("Device:",deviceEntity))));
    }
    @Test
    void addServiceToDevice() throws Exception {
        when(deviceService.getDeviceEntity(deviceId)).thenReturn(Optional.of(deviceEntity));
        when(deviceService.saveDeviceEntity(deviceEntity)).thenReturn(deviceEntity);
        when(servicesService.getServiceEntity(serviceId)).thenReturn(Optional.of(serviceEntity));
        mockMvc.perform(post("/device/" + serviceId + "/service/" + serviceId))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(deviceEntity)));
    }

    @Test
    void getCostOfDevice() throws Exception {
        deviceEntity.getServices().add(serviceEntity);
        when(deviceService.getDeviceEntity(deviceId)).thenReturn(Optional.of(deviceEntity));
        mockMvc.perform(get("/device/" + deviceId + "/cost"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"Total cost\":11.0}"));
    }


    @Test
    void DeleteDevice() throws Exception {
        deviceEntity.getServices().add(serviceEntity);
        when(deviceService.getDeviceEntity(deviceId)).thenReturn(Optional.of(deviceEntity));

        mockMvc.perform(delete("/device/" + deviceId ))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
}
