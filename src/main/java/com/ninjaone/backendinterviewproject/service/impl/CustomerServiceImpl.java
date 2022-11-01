package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.dto.CustomerDto;
import com.ninjaone.backendinterviewproject.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.dto.ServiceDto;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceByDevice;
import com.ninjaone.backendinterviewproject.repository.CustomerRepository;
import com.ninjaone.backendinterviewproject.repository.ServicesByDeviceRepository;
import com.ninjaone.backendinterviewproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    public static final int MAC_ID = 2;
    public static final int ANTIVIRUS_ID = 2;
    private final CustomerRepository customerRepository;
    private final ServicesByDeviceRepository servicesByDeviceRepository;

    @Override
    @Transactional
    public String registerCustomerWithDeviceAndServices(CustomerDto customerDto) {

        Customer customer = createCustomer(customerDto);
        createServicesByDevices(customerDto, customer);
        return "Process Ok";
    }

    private void createServicesByDevices(CustomerDto customerDto, Customer customer) {
        for (DeviceDto deviceDto: customerDto.getDevices()) {
            for (ServiceDto serviceDto: deviceDto.getServices() ) {
                ServiceByDevice serviceByDevice =  ServiceByDevice.builder()
                        .device(Device.builder()
                                .id(deviceDto.getId())
                                .build())
                        .service(com.ninjaone.backendinterviewproject.model.Service.builder()
                                .id(serviceDto.getId())
                                .build())
                        .customer(customer)
                        .build();
                servicesByDeviceRepository.save(serviceByDevice);
            }
        }
    }

    private Customer createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .build();

        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    @Transactional(readOnly=true)
    public String getCustomerTotalBillMonthly(String customerId) {

        List<ServiceByDevice> devicesList= servicesByDeviceRepository.findDevicesWithServicesByCustomerId(Integer.parseInt(customerId));
        var totalResult=0.0;
        for (ServiceByDevice serviceByDevice:devicesList) {
            var serviceCost =serviceByDevice.getService().getCost();
            if(serviceByDevice.getService().getId() == ANTIVIRUS_ID &&  serviceByDevice.getDevice().getType().getId() == MAC_ID){
                serviceCost = serviceCost+2;
            }
            totalResult=totalResult+serviceCost;
        }

        return "Total monthly = "+ Double.toString(totalResult);
    }
}
