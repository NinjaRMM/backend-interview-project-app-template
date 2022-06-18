package com.ninjaone.backendinterviewproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.model.Authority;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.model.UserSystem;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.UserSystemServiceInterface;

@SpringBootApplication
public class BackendInterviewProjectApplication {
	private static final String CUSTOMER_ROLE = "CUSTOMER";
	private static final String ADMIN_ROLE = "ADMIN";
	public static final String customerId = "12345";

	public static void main(String[] args) {
		SpringApplication.run(BackendInterviewProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserSystemServiceInterface userSystemServiceInterface,
			CustomerServiceInterface customerServiceInterface, ServiceNinjaOneServiceInterface serviceNinjaOneServiceInterface) {
		return args -> {
			// user system creation
			UserSystem userSystemCustomer = new UserSystem("customer", "customer");
			userSystemCustomer.setAuthorities(Arrays.asList(new Authority(CUSTOMER_ROLE)));
			userSystemServiceInterface.create(userSystemCustomer);
			UserSystem userSystemAdmin = new UserSystem("admin", "admin");
			userSystemAdmin.setAuthorities(Arrays.asList(new Authority(ADMIN_ROLE)));
			userSystemServiceInterface.create(userSystemAdmin);

			// customer creation
			List<DeviceNinjaOne> listOfDeviceNinjaOne = new ArrayList<>();
			listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC01", "WINDOWS WORKSTATION", "MICROSOFT WINDOWS", "12345"));
			listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC02", "WINDOWS SERVER", "MICROSOFT WINDOWS", "12345"));
			listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC03", "MAC", "APPLE MACOS", "12345"));
			listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC04", "MAC", "APPLE MACOS", "12345"));
			listOfDeviceNinjaOne.add(new DeviceNinjaOne("PC05", "MAC", "APPLE MACOS", "12345"));

			Customer customer = new Customer(customerId);
			customer.setDevices(listOfDeviceNinjaOne);
			customerServiceInterface.create(customer);

	// service creation
			Set<ServiceNinjaOne> listOfServiceNinjaOne = new HashSet<>();
			listOfServiceNinjaOne.add(new ServiceNinjaOne("DEVICE", "GENERIC", 4.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne("ANTIVIRUS", "MICROSOFT WINDOWS", 5.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne("ANTIVIRUS", "APPLE MACOS", 7.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne("BACKUP", "GENERIC", 3.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne("PSA", "GENERIC", 2.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne("SCREEN SHARE", "GENERIC", 1.00));

			listOfServiceNinjaOne.stream().forEach(elem -> {
				try {
					serviceNinjaOneServiceInterface.create(elem);
				} catch (GenericException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		};
	}
}
