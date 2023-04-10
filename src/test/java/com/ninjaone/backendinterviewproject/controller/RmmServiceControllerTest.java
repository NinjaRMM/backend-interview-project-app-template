package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.dto.input.RmmServiceRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceResponseDto;
import com.ninjaone.backendinterviewproject.exception.ConflictException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.RmmServiceService;
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

@WebMvcTest(RmmServiceController.class)
@ExtendWith(MockitoExtension.class)
class RmmServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RmmServiceService rmmServiceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addServiceTest() throws Exception {
        RmmServiceRequestDto requestDto = new RmmServiceRequestDto(2f, "description1", DeviceType.MAC);
        RmmServiceResponseDto responseDto = new RmmServiceResponseDto();
        responseDto.setCost(2f);
        responseDto.setName("description1");
        responseDto.setDeviceType(DeviceType.MAC);

        when(rmmServiceService.create(any())).thenReturn(responseDto);

        mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    public void addServiceTestWithDuplicatedRecord() throws Exception {
        RmmServiceRequestDto requestDto = new RmmServiceRequestDto(2f, "description1", DeviceType.MAC);
        RmmServiceResponseDto responseDto = new RmmServiceResponseDto();
        responseDto.setCost(2f);
        responseDto.setName("description1");
        responseDto.setDeviceType(DeviceType.MAC);

        when(rmmServiceService.create(any())).thenThrow(new ConflictException("duplicated record"));

        mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void getServiceById() throws Exception {
        RmmServiceResponseDto mock = new RmmServiceResponseDto();
        mock.setName("name");
        mock.setDeviceType(DeviceType.MAC);

        when(rmmServiceService.getById(1L)).thenReturn(mock);

        mockMvc.perform(get("/service/" + 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(mock)));
    }

    @Test
    void getServiceByIdWithNoResourcesFound() throws Exception {
        when(rmmServiceService.getById(1L)).thenThrow(new ResourceNotFoundException("test"));
        mockMvc.perform(get("/service/" + 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void removeRmmService() throws Exception {
        doNothing().when(rmmServiceService).deleteById(1L);

        mockMvc.perform(delete("/service/" + 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeRmmServiceWithNoResourcesFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(0)).when(rmmServiceService).deleteById(1L);
        mockMvc.perform(delete("/service/" + 1L))
                .andExpect(status().isNotFound());
    }

}