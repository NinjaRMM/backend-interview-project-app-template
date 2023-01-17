package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ITServiceRepository;
import com.ninjaone.backendinterviewproject.model.ITService;
import com.ninjaone.backendinterviewproject.model.enums.DeviceType;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ITServiceTest {

    @Mock
    private ITServiceRepository repository;

    @InjectMocks
    private ITServiceService service;

    @Test
    public void givenACompleteServiceWhenTryingToInsertItThenInsertItSuccessfully() {
        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );

        when(repository.save(testItService)).thenReturn(testItService);

        Assertions.assertEquals(testItService, service.saveService(testItService));
    }

    @Test
    public void givenAnExistingServiceWhenTryingToReInsertItThenThrowException() {
        //GIVEN
        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );
        //WHEN
        doThrow(ConstraintViolationException.class).when(repository).save(testItService);

        //THEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.saveService(testItService));
    }

    @Test
    public void givenAnExistingServiceWhenTryingToDeleteItByIDThenDeleteItSuccessfully() {
        //GIVEN
        doNothing().when(repository).deleteById(456L);
        repository.deleteById(456L);
        Mockito.verify(repository, times(1)).deleteById(456L);
    }

    @Test
    public void givenNoExistingServiceWithIdWHENTryingToDeleteItThenThrowConstraintViolationException() {
        doThrow(ConstraintViolationException.class).when(repository).deleteById(456L);

        Assertions.assertThrows(ConstraintViolationException.class, () -> service.deleteService(456L));
    }
}
