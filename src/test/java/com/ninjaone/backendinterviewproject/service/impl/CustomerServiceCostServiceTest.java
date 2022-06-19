package com.ninjaone.backendinterviewproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceCostServiceTest {

	public static final String customerId = "12345";

	@Mock
	private CustomerServiceInterface customerServiceInterface;

	@InjectMocks
	private CustomerServiceCostService customerServiceCostService;

	private Customer customer;

	@BeforeEach
	void setup() {
		List<DeviceNinjaOne> listOfDeviceNinjaOne = new ArrayList<>();
		listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC01","WINDOWS WORKSTATION","MICROSOFT WINDOWS", "12345"));
		listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC02","WINDOWS SERVER","MICROSOFT WINDOWS","12345"));
		listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC03","MAC","APPLE MACOS","12345"));
		listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC04","MAC", "APPLE MACOS","12345"));
		listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC05","MAC","APPLE MACOS","12345"));
		Set<ServiceNinjaOne> listOfServiceNinjaOne = new HashSet<>();
		listOfServiceNinjaOne.add(new ServiceNinjaOne("DEVICE", "GENERIC", 4.00));
		listOfServiceNinjaOne.add(new ServiceNinjaOne("ANTIVIRUS", "MICROSOFT WINDOWS", 5.00));
		listOfServiceNinjaOne.add(new ServiceNinjaOne("ANTIVIRUS", "APPLE MACOS", 7.00));
		listOfServiceNinjaOne.add(new ServiceNinjaOne("BACKUP", "GENERIC", 3.00));
		listOfServiceNinjaOne.add(new ServiceNinjaOne("SCREEN SHARE", "GENERIC", 1.00));
		customer = new Customer(customerId);
		customer.setDevices(listOfDeviceNinjaOne);
		customer.setServices(listOfServiceNinjaOne);
	}


	@Test
	public void testGetCostOfAllServicesByCustomerId() throws Exception {
        when(customerServiceInterface.findById(customerId)).thenReturn(customer);
		double expectedCost = 71;
        double actualCost = customerServiceCostService.getCostOfServicesByCustomerId(customerId);
        assertEquals(expectedCost, actualCost);
	}

}
