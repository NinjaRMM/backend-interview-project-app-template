package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ITServiceRepository;
import com.ninjaone.backendinterviewproject.exceptions.DeviceNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotAvailableForDeviceException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotFoundException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ITService;
import com.ninjaone.backendinterviewproject.model.enums.DeviceType;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository repository;

    @Mock
    private ITServiceRepository serviceRepository;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private DeviceService service;

    @Test
    public void givenACompleteAndCorrectDeviceEntityWhenTryingToInsertItThenInsertItAndReturn() {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.ANY,
                "Personal Computer 123",
                null
        );

        //WHEN
        when(repository.save(testDevice)).thenReturn(testDevice);
        //THEN
        Device response = service.saveDeviceEntity(testDevice);

        Assertions.assertEquals(testDevice, response);

    }

    @Test
    public void givenAnExistingDeviceEntityWhenTryingToInsertItThenThrowAnException() {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.ANY,
                "Personal Computer 123",
                null
        );

        //WHEN
        doThrow(ConstraintViolationException.class).when(repository).save(testDevice);

        //THEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.saveDeviceEntity(testDevice));
    }

    @Test
    public void givenAnExistingDeviceWithMatchingIdWHENTryingToDeleteItThenDeleteItSuccessfully() {
        doNothing().when(repository).deleteById(123L);
        repository.deleteById(123L);
        Mockito.verify(repository, times(1)).deleteById(123L);
    }

    @Test
    public void givenNoExistingDeviceWithIdWHENTryingToDeleteItThenThrowConstraintViolationException() {
        doThrow(ConstraintViolationException.class).when(repository).deleteById(123L);

        Assertions.assertThrows(ConstraintViolationException.class, () -> service.deleteDeviceEntityById(123L));
    }

    @Test
    public void givenAnExistingDeviceInDBWhenAskingToRetrieveItThenReturnItCorrectly() throws DeviceNotFoundException {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.ANY,
                "Personal Computer 123",
                null
        );
        when(repository.findById(123L)).thenReturn(Optional.of(testDevice));

        //WHEN
        Device response = service.getDeviceById(123L);

        //THEN
        Assertions.assertEquals(testDevice, response);
    }

    @Test
    public void givenNoExistingDeviceInDBWhenAskingToRetrieveItThenThrowDeviceNotFoundException() {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.ANY,
                "Personal Computer 123",
                null
        );

        //WHEN
        //THEN
        Assertions.assertThrows(DeviceNotFoundException.class, () -> service.getDeviceById(123L));
    }

    @Test
    public void givenAnExistingDeviceAndServiceInDBWhenAskingToLinkTheServiceToTheDeviceThenLinkProperly() throws ServiceNotAvailableForDeviceException, DeviceNotFoundException, ServiceNotFoundException {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.ANY,
                "Personal Computer 123",
                new HashSet<>()
        );

        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );

        //WHEN

        when(repository.findById(123L)).thenReturn(Optional.of(testDevice));
        when(serviceRepository.findById(456L)).thenReturn(Optional.of(testItService));
        doNothing().when(reportService).resetCache();
        //THEN

        ArgumentCaptor<Device> captor = ArgumentCaptor.forClass(Device.class);
        service.addServiceToDevice(123L, 456L);
        Mockito.verify(reportService, times(1)).resetCache();

        verify(repository).save(captor.capture());
        Device sentDevice = captor.getValue();
        Assertions.assertTrue(sentDevice.getLinkedServices().contains(testItService));
    }

    @Test
    public void givenNoExistingDeviceOrServiceInDBWhenAskingToLinkTheServiceToTheDeviceThenThrowException() throws ServiceNotAvailableForDeviceException, DeviceNotFoundException, ServiceNotFoundException {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.ANY,
                "Personal Computer 123",
                new HashSet<>()
        );

        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );

        //WHEN
        //THEN

        Assertions.assertThrows(DeviceNotFoundException.class, () -> service.addServiceToDevice(123L, 456L));
    }

    @Test
    public void givenAnExistingDeviceAndServiceInDBWhenAskingToLinkTheServiceToTheDeviceButTheServiceDevicesAndTheDeviceTypeDoNotMatchThenThrowException() throws ServiceNotAvailableForDeviceException, DeviceNotFoundException, ServiceNotFoundException {
        //GIVEN
        Device testDevice = new Device(
                123,
                DeviceType.WINDOWS_WORKSTATION,
                "Personal Computer 123",
                new HashSet<>()
        );

        List<DeviceType> devices = Arrays.asList(DeviceType.MAC, DeviceType.WINDOWS_SERVER);
        ITService testItService = new ITService(456L, "Antivirus", "This is an Antivirus", 10, devices, new HashSet<>() );

        //WHEN

        when(repository.findById(123L)).thenReturn(Optional.of(testDevice));
        when(serviceRepository.findById(456L)).thenReturn(Optional.of(testItService));
        //THEN


        Assertions.assertThrows(ServiceNotAvailableForDeviceException.class, () -> service.addServiceToDevice(123L, 456L));
    }
}
