package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.response.InvoiceDTO;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceServiceDeviceType;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.Interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private IInvoiceService IInvoiceService;

    public InvoiceController(IInvoiceService IInvoiceService) {
        this.IInvoiceService = IInvoiceService;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Customer putSampleEntity(@RequestBody Customer customer){
        return IInvoiceService.saveDeviceEntity(customer);
    }

    @GetMapping("/{id}")
    private Customer getSampleEntity(@PathVariable String id){
        return IInvoiceService.getDeviceEntity(id)
                .orElseThrow();
    }
    @GetMapping("/summary/{id}")
    private InvoiceDTO getInvoiceDetails(@PathVariable String id){
        return IInvoiceService.getInvoiceDetails(id)
                .orElseThrow();
    }

    @PostMapping("/addItem")
    @ResponseStatus(HttpStatus.CREATED)
    private DeviceServiceDeviceType postAddInvoiceDetails(@RequestBody DeviceServiceDeviceType deviceServiceDeviceType){
        return IInvoiceService.addDeviceServiceDeviceType(deviceServiceDeviceType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteSampleEntity(@PathVariable String id){
        IInvoiceService.deleteDeviceServiceDeviceType(id);
    }
}
