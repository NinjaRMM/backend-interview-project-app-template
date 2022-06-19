package com.ninjaone.backendinterviewproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ninjaone.backendinterviewproject.database.DeviceNinjaOneRepository;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;

@ExtendWith(MockitoExtension.class)
public class DeviceNinjaOneServiceTest {

	@Mock
	private DeviceNinjaOneRepository deviceNinjaOneRepository;

	@InjectMocks
	private DeviceNinjaOneService deviceNinjaOneService;

	DeviceNinjaOne deviceNinjaOne;

	@BeforeEach
	void setup() {
		deviceNinjaOne = new DeviceNinjaOne("PC01", "WINDOWS WORKSTATION", "MICROSOFT WINDOWS", "12345");
	}

	@Test
	public void testServiceNinjaOneServiceCreate() throws Exception {
		when(deviceNinjaOneRepository.findBySystemNameAndOperatingSystemTypeAndCustomer(
				deviceNinjaOne.getSystemName(), deviceNinjaOne.getOperatingSystemType(),
				deviceNinjaOne.getCustomer())).thenReturn(Optional.of(deviceNinjaOne));
		Exception exception = assertThrows(Exception.class, () -> deviceNinjaOneService.create(deviceNinjaOne));
		String errorMessage = "The device name: " + deviceNinjaOne.getSystemName() + " and operating system type "
				+ deviceNinjaOne.getOperatingSystemType().getId() + " and customer: "
				+ deviceNinjaOne.getCustomer().getId() + "already exists";
		assertEquals(errorMessage, exception.getMessage());
	}

}
