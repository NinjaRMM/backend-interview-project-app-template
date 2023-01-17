package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.controller.responses.RestControllerErrorTemplate;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ITService;
import com.ninjaone.backendinterviewproject.model.enums.DeviceType;
import com.ninjaone.backendinterviewproject.service.ITServiceService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(ITServiceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ITServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ITServiceService service;

    @Test
    public void givenANonExistentDeviceWhenTryingToPostItThenCallTheMethodAndReturnTheEntity() throws Exception {
        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );

        when(service.saveService(any())).thenReturn(testItService);

        String response = mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testItService))
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(testItService), response);
    }

    @Test
    public void givenAnExistentDeviceWhenTryingToPostItThenCallTheMethodAndThrowAnErrorMessage() throws Exception {
        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );

        when(service.saveService(any())).thenThrow(ConstraintViolationException.class);

        String response = mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testItService))
                )
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(new RestControllerErrorTemplate("There is a property passed as parameter that don't allow duplicates and already exists in the database, please rectify.", 400, "ERROR")), response);
    }

    @Test
    public void givenAnExistentDeviceWhenTryingToDeleteItByIdThenReturnOK() throws Exception {
        doNothing().when(service).deleteService(any());
        mockMvc.perform(delete("/service/123"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenANonExistentDeviceWhenTryingToDeleteItByIdThenReturnCustomErrorMessage() throws Exception {
        doThrow(EmptyResultDataAccessException.class).when(service).deleteService(any());
        String response = mockMvc.perform(delete("/service/123"))
                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(new RestControllerErrorTemplate("Couldn't find the object requested by the ID passed as parameter, please check.", 404, "ERROR")), response);
    }

}
