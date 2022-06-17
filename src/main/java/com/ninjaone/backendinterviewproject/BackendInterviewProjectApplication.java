package com.ninjaone.backendinterviewproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ninjaone.backendinterviewproject.model.Authority;
import com.ninjaone.backendinterviewproject.model.Customer;
import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.model.UserSystem;
import com.ninjaone.backendinterviewproject.service.api.CustomerServiceInterface;
import com.ninjaone.backendinterviewproject.service.api.ServiceServiceInterface;
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
			CustomerServiceInterface customerServiceInterface, ServiceServiceInterface serviceServiceInterface) {
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
			/* 
			Set<ServiceNinjaOne> listOfServiceNinjaOne = new HashSet<>();
			listOfServiceNinjaOne.add(new ServiceNinjaOne(Long.valueOf(1),"DEVICE", "GENERIC", 4.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne(Long.valueOf(2),"ANTIVIRUS", "MICROSOFT WINDOWS", 5.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne(Long.valueOf(3),"ANTIVIRUS", "APPLE MACOS", 7.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne(Long.valueOf(4),"BACKUP", "GENERIC", 3.00));
			listOfServiceNinjaOne.add(new ServiceNinjaOne(Long.valueOf(6),"SCREEN SHARE", "GENERIC", 1.00));
			*/
			Customer customer = new Customer(customerId);
			customer.setDevices(listOfDeviceNinjaOne);
			//customer.setServices(listOfServiceNinjaOne);
			customerServiceInterface.create(customer);

			// add service to customer
			/* 
			serviceServiceInterface.addServiceToCustomer(Long.valueOf(1), customer.getId());
			serviceServiceInterface.addServiceToCustomer(Long.valueOf(2), customer.getId());
			serviceServiceInterface.addServiceToCustomer(Long.valueOf(3), customer.getId());
			serviceServiceInterface.addServiceToCustomer(Long.valueOf(4), customer.getId());
			serviceServiceInterface.addServiceToCustomer(Long.valueOf(6), customer.getId());
			*/


		};
	}
}
