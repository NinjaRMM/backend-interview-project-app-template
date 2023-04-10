package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.dto.input.DeviceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.DeviceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ConflictException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addDeviceTest() throws Exception {
        DeviceRequestDto requestDto = new DeviceRequestDto("systemName", DeviceType.MAC);
        DeviceResponseDto responseDto = new DeviceResponseDto();
        responseDto.setSystemName("systemName");
        responseDto.setDeviceType(DeviceType.MAC.name());

        when(deviceService.create(any())).thenReturn(responseDto);

        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    public void addDeviceWithDuplicatedRecordTest() throws Exception {
        DeviceRequestDto requestDto = new DeviceRequestDto("systemName", DeviceType.MAC);

        when(deviceService.create(any())).thenThrow(new ConflictException("duplicated record"));

        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void getDeviceById() throws Exception {
        DeviceResponseDto mock = new DeviceResponseDto();
        mock.setSystemName("systemName");
        mock.setDeviceType(DeviceType.MAC.name());
        mock.setId(1L);

        when(deviceService.getById(1L)).thenReturn(mock);

        mockMvc.perform(get("/device/" + 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(mock)));
    }

    @Test
    void getDeviceByIdWithNoResourcesFound() throws Exception {
        when(deviceService.getById(1L)).thenThrow(new ResourceNotFoundException("test"));
        mockMvc.perform(get("/device/" + 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void removeDevice() throws Exception {
        doNothing().when(deviceService).deleteById(1L);

        mockMvc.perform(delete("/device/" + 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeDeviceWithNoResourcesFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(0)).when(deviceService).deleteById(1L);
        mockMvc.perform(delete("/device/" + 1L))
                .andExpect(status().isNotFound());
    }

}