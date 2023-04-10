package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.dto.input.RmmServiceExecutionRequestDto;
import com.ninjaone.backendinterviewproject.dto.output.ExecutedServicesByDeviceResponseDto;
import com.ninjaone.backendinterviewproject.dto.output.RmmServiceExecutionResponseDto;
import com.ninjaone.backendinterviewproject.exception.BadRequestException;
import com.ninjaone.backendinterviewproject.exception.ResourceNotFoundException;
import com.ninjaone.backendinterviewproject.mock.CostsByDeviceResponseDtoMock;
import com.ninjaone.backendinterviewproject.service.RmmServiceExecutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RmmServiceExecutionController.class)
@AutoConfigureMockMvc
public class RmmServiceExecutionControllerTest {

    private static final Long DEVICE_ID = 123L;
    private static final Long SERVICE_EXECUTION_ID = 456L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RmmServiceExecutionService rmmServiceExecutionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addService() throws Exception {
        RmmServiceExecutionRequestDto dto = new RmmServiceExecutionRequestDto(123L, 456L, 3);
        RmmServiceExecutionResponseDto responseDto = getRmmServiceExecutionResponseDtoMock();

        when(rmmServiceExecutionService.create(any(RmmServiceExecutionRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/service-execution")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void addServiceWithInexistingDeviceOrRmmService() throws Exception {
        RmmServiceExecutionRequestDto dto = new RmmServiceExecutionRequestDto(123L, 456L, 3);

        when(rmmServiceExecutionService.create(any(RmmServiceExecutionRequestDto.class))).thenThrow(new ResourceNotFoundException("Test"));

        mockMvc.perform(post("/service-execution")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void addServiceWithDeviceAndRmmServiceOfDifferentTypes() throws Exception {
        RmmServiceExecutionRequestDto dto = new RmmServiceExecutionRequestDto(123L, 456L, 3);

        when(rmmServiceExecutionService.create(any(RmmServiceExecutionRequestDto.class))).thenThrow(new BadRequestException("Test"));

        mockMvc.perform(post("/service-execution")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    private RmmServiceExecutionResponseDto getRmmServiceExecutionResponseDtoMock() {
        RmmServiceExecutionResponseDto responseDto = new RmmServiceExecutionResponseDto();
        responseDto.setId(1L);
        responseDto.setDeviceId(123L);
        responseDto.setRmmServiceId(456L);
        responseDto.setQuantity(3);
        responseDto.setExecutionDateTime(LocalDateTime.now());
        return responseDto;
    }

    @Test
    void removeServiceExecution() throws Exception {
        doNothing().when(rmmServiceExecutionService).deleteById(SERVICE_EXECUTION_ID);

        mockMvc.perform(delete("/service-execution/" + SERVICE_EXECUTION_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeServiceExecutionWithNotFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(0)).when(rmmServiceExecutionService).deleteById(SERVICE_EXECUTION_ID);

        mockMvc.perform(delete("/service-execution/" + SERVICE_EXECUTION_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void calculateCostsByDeviceId() throws Exception {
        CostsByDeviceResponseDtoMock mock = new CostsByDeviceResponseDtoMock();

        when(rmmServiceExecutionService.calculateCostsByDeviceId(DEVICE_ID)).thenReturn(mock);

        mockMvc.perform(get("/service-execution/calculate-by-device/" + DEVICE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(mock)));
    }

    @Test
    void calculateCostsByDeviceIdWithNoResourcesFound() throws Exception {
        when(rmmServiceExecutionService.calculateCostsByDeviceId(DEVICE_ID)).thenThrow(new ResourceNotFoundException("test"));
        mockMvc.perform(get("/service-execution/calculate-by-device/" + DEVICE_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void getExecutedServicesByDeviceId() throws Exception {
        List<ExecutedServicesByDeviceResponseDto> mock = new ArrayList<>();
        when(rmmServiceExecutionService.getExecutedServicesByDeviceId(DEVICE_ID)).thenReturn(mock);

        mockMvc.perform(get("/service-execution/executed-by-device/" + DEVICE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(mock)));
    }

    @Test
    void getExecutedServicesByDeviceIdWithNoResourcesFound() throws Exception {
        when(rmmServiceExecutionService.getExecutedServicesByDeviceId(DEVICE_ID)).thenThrow(new ResourceNotFoundException("test"));
        mockMvc.perform(get("/service-execution/executed-by-device/" + DEVICE_ID))
                .andExpect(status().isNotFound());
    }

}