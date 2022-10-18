package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.DeviceServiceDeviceTypeRepository;
import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.dto.response.InvoiceDTO;
import com.ninjaone.backendinterviewproject.dto.response.InvoiceItemDTO;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceServiceDeviceType;
import com.ninjaone.backendinterviewproject.service.Interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private DeviceServiceDeviceTypeRepository deviceServiceDeviceTypeRepository;

    public InvoiceService(CustomerRepository customerRepository, DeviceServiceDeviceTypeRepository deviceServiceDeviceTypeRepository, DeviceTypeRepository deviceTypeRepository) {
        this.customerRepository = customerRepository;
        this.deviceServiceDeviceTypeRepository = deviceServiceDeviceTypeRepository;
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Override
    public Customer saveDeviceEntity(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getDeviceEntity(String id){
        return customerRepository.findById(id);
    }

    @Override
    public Optional<InvoiceDTO> getInvoiceDetails(String id){
        Optional<Customer> customer = customerRepository.findById(id);
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceItemDTOS(new ArrayList<InvoiceItemDTO>());
        double total = 0;
        for (Device device : customer.get().getDevice())
        {
            for (DeviceServiceDeviceType deviceServiceDeviceType : device.getDeviceServiceDeviceType()) {
                InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO();
                invoiceItemDTO.setNumberOfItems(1);
                invoiceItemDTO.setItemDeviceName(device.getDeviceName());
                invoiceItemDTO.setItemServiceName(deviceServiceDeviceType.getServiceDeviceType().getServiceOne().getServiceName());
                invoiceItemDTO.setItemUnitCost(deviceServiceDeviceType.getServiceDeviceType().getServiceCost());
                total += invoiceItemDTO.getItemUnitCost();
                invoiceDTO.getInvoiceItemDTOS().add(invoiceItemDTO);
            }
        }
        invoiceDTO.setCustomerName(customer.get().getCustomerName());
        invoiceDTO.setInvoiceTotal(total);
        return Optional.of(invoiceDTO);
    }


    @Override
    public DeviceServiceDeviceType addDeviceServiceDeviceType(DeviceServiceDeviceType deviceServiceDeviceType){

//        deviceServiceDeviceType.getDevice().setDeviceType(deviceTypeRepository.findById(deviceServiceDeviceType.getDevice().getDeviceTypeId()).get());
        return deviceServiceDeviceTypeRepository.save(deviceServiceDeviceType);
    }

    @Override
    public void deleteDeviceServiceDeviceType(String id) {
        deviceServiceDeviceTypeRepository.deleteById(id);
    }

    public DeviceTypeRepository getDeviceTypeRepository() {
        return deviceTypeRepository;
    }

    public void setDeviceTypeRepository(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }
}
