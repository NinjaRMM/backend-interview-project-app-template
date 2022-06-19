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

import com.ninjaone.backendinterviewproject.database.ServiceNinjaOneRepository;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;

@ExtendWith(MockitoExtension.class)
public class ServiceNinjaOneServiceTest {

	@Mock
	private ServiceNinjaOneRepository serviceNinjaOneRepository;

	@InjectMocks
	private ServiceNinjaOneService serviceNinjaOneService;

	ServiceNinjaOne serviceNinjaOne;

	@BeforeEach
	void setup() {
		serviceNinjaOne = new ServiceNinjaOne("DEVICE", "GENERIC", 4.00);
	}

	@Test
	public void testServiceNinjaOneServiceCreate() throws Exception {
		when(serviceNinjaOneRepository.findByServiceNameAndOperatingSystem(serviceNinjaOne.getServiceName(),
				serviceNinjaOne.getOperatingSystem())).thenReturn(Optional.of(serviceNinjaOne));
		Exception exception = assertThrows(Exception.class, () -> serviceNinjaOneService.create(serviceNinjaOne));
		String errorMessage = "The service name: " + serviceNinjaOne.getServiceName() + " and operating system:"
				+ serviceNinjaOne.getOperatingSystem().getId() + " already exists";
		assertEquals(errorMessage, exception.getMessage());
	}

}
