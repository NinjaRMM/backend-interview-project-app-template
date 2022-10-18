package com.ninjaone.backendinterviewproject.service.Interfaces;

import com.ninjaone.backendinterviewproject.dto.response.InvoiceDTO;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceServiceDeviceType;

import java.util.Optional;

public interface IInvoiceService {
    Customer saveDeviceEntity(Customer customer);

    Optional<Customer> getDeviceEntity(String id);

    Optional<InvoiceDTO> getInvoiceDetails(String id);

    DeviceServiceDeviceType addDeviceServiceDeviceType(DeviceServiceDeviceType deviceServiceDeviceType);

    void deleteDeviceServiceDeviceType(String id);
}
