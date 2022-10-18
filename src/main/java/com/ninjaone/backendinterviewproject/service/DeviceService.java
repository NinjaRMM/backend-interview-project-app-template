package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.dto.request.NewDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.request.UpdateDeviceRequest;
import com.ninjaone.backendinterviewproject.dto.response.DeviceDTO;
import com.ninjaone.backendinterviewproject.exception.CustomerNotFoundException;
import com.ninjaone.backendinterviewproject.exception.DeviceNotFoundException;
import com.ninjaone.backendinterviewproject.exception.DeviceTypeNotFoundException;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.Interfaces.IDeviceService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService implements IDeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    private final ModelMapper modelMapper;

    public DeviceService(DeviceRepository deviceRepository, CustomerRepository customerRepository, DeviceTypeRepository deviceTypeRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.modelMapper = modelMapper;
        if (modelMapper.getConfiguration()!= null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        }
    }

    @Override
    public DeviceDTO saveDevice(NewDeviceRequest newDeviceRequest) {

        final Customer customer = this.customerRepository.findById(newDeviceRequest.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);

        final DeviceType deviceType = this.deviceTypeRepository.findById(newDeviceRequest.getDeviceTypeId())
                .orElseThrow(DeviceTypeNotFoundException::new);

        final Device newDevice = new Device(newDeviceRequest.getDeviceName(), customer, newDeviceRequest.getDeviceTypeId());
        DeviceDTO deviceDTOToReturn = modelMapper.map(deviceRepository.save(newDevice), DeviceDTO.class);
        return deviceDTOToReturn;
    }

    @Override
    public DeviceDTO updateDevice(UpdateDeviceRequest updateDeviceRequest) {

        final Device device = this.deviceRepository.findById(updateDeviceRequest.getDeviceId())
                .orElseThrow(DeviceTypeNotFoundException::new);

        final Customer customer = this.customerRepository.findById(updateDeviceRequest.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);

        device.setDeviceName(updateDeviceRequest.getDeviceName());
        device.setCustomer(customer);

        return modelMapper.map(deviceRepository.save(device), DeviceDTO.class);
    }

    public DeviceDTO getDeviceById(Long id) {
        final Device device = deviceRepository
                .findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(String.format("Device not found with id %s", id)));
        return modelMapper.map(device, DeviceDTO.class);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}
