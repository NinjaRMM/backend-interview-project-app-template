package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.dto.request.NewDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.request.UpdateDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
    public static final Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    HttpHeaders httpHeaders= new HttpHeaders();

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    private DeviceDTO deviceEntity;
    private NewDeviceRequest newDeviceRequest;
    private UpdateDeviceRequest updateDeviceRequest;

    @BeforeEach
    void setup(){
        deviceEntity = new DeviceDTO("1","testDevice","testDeviceType");
        httpHeaders.add("Authorization","Basic YWRtaW46cGFzc3dvcmQ=");
    }

    @Test
    void getSampleData() throws Exception {
        when(deviceService.getDeviceById(ID)).thenReturn(deviceEntity);
        mockMvc.perform(get("/device/" + ID).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(deviceEntity)));
    }

    @Test
    void postSampleData() throws Exception {
        newDeviceRequest = new NewDeviceRequest();
        newDeviceRequest.setDeviceName("test");
        newDeviceRequest.setDeviceTypeId("MAC");
        newDeviceRequest.setCustomerId("C1");
        when(deviceService.saveDevice(any())).thenReturn(deviceEntity);

        String sampleEntityString = objectMapper.writeValueAsString(newDeviceRequest);
        String sampleResponseString = objectMapper.writeValueAsString(deviceEntity);
        mockMvc.perform(post("/device").headers(httpHeaders)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sampleEntityString))
                .andExpect(status().isCreated())
                .andExpect(content().string(sampleResponseString));
    }

    @Test
    void putSampleData() throws Exception {
        newDeviceRequest = new NewDeviceRequest();
        newDeviceRequest.setDeviceName("test");
        newDeviceRequest.setDeviceTypeId("MAC");
        newDeviceRequest.setCustomerId("C1");
        when(deviceService.saveDevice(any())).thenReturn(deviceEntity);

        String sampleEntityString = objectMapper.writeValueAsString(newDeviceRequest);
        String sampleResponseString = objectMapper.writeValueAsString(deviceEntity);
        mockMvc.perform(put("/device/"+ID).headers(httpHeaders)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sampleEntityString))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deleteSampleData() throws Exception {
        doNothing().when(deviceService).deleteDevice(ID);

        mockMvc.perform(delete("/device/" + ID).headers(httpHeaders))
                .andExpect(status().isNoContent());
    }
}
