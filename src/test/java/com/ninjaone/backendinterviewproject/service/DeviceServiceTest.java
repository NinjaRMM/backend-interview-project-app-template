package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.dto.request.NewDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    public static final Long ID = 1L;

    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DeviceTypeRepository deviceTypeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DeviceService deviceService;

    private Device deviceEntity;
    private DeviceDTO deviceDTO;

    @BeforeEach
    void setup(){
        deviceEntity = new Device(1L, "testDevice", new Customer(), "DT1");
        deviceDTO = new DeviceDTO("1","testDevice","testDeviceType");
    }

    @Test
    void getSampleData() {
        when(deviceRepository.findById(ID)).thenReturn(Optional.of(deviceEntity));
        when(modelMapper.map(deviceEntity, DeviceDTO.class)).thenReturn(deviceDTO);

        DeviceDTO sampleEntityOptional = deviceService.getDeviceById(ID);
        DeviceDTO actualEntity = sampleEntityOptional;

        assert actualEntity != null;
        assertEquals(deviceEntity.getId().toString(), actualEntity.getId());
    }

    @Test
    void saveSampleData() {
        NewDeviceRequest newDeviceRequest = new NewDeviceRequest();
        newDeviceRequest.setDeviceName(deviceEntity.getDeviceName());
        newDeviceRequest.setDeviceTypeId(deviceEntity.getDeviceTypeId());
        newDeviceRequest.setCustomerId(deviceEntity.getCustomer().getId());
        when(deviceRepository.save((Device) notNull())).thenReturn(deviceEntity);
        when(customerRepository.findById(null)).thenReturn(Optional.of(new Customer()));
        when(deviceTypeRepository.findById("DT1")).thenReturn(Optional.of(new DeviceType()));
        when(modelMapper.map(deviceEntity, DeviceDTO.class)).thenReturn(deviceDTO);

        DeviceDTO deviceDTOResponse = deviceService.saveDevice(newDeviceRequest);

        assertEquals(deviceDTO, deviceDTOResponse);
    }

    @Test
    void deleteSampleData(){
        doNothing().when(deviceRepository).deleteById(ID);
        deviceService.deleteDevice(ID);
        Mockito.verify(deviceRepository, times(1)).deleteById(ID);
    }
}
