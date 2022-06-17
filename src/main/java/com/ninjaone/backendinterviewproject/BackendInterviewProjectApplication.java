package com.ninjaone.backendinterviewproject;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ninjaone.backendinterviewproject.model.Authority;
import com.ninjaone.backendinterviewproject.model.UserSystem;
import com.ninjaone.backendinterviewproject.service.api.UserSystemServiceInterface;

@SpringBootApplication
public class BackendInterviewProjectApplication {
	private static final String CUSTOMER_ROLE = "CUSTOMER";
    private static final String ADMIN_ROLE = "ADMIN";
	public static void main(String[] args) {
		SpringApplication.run(BackendInterviewProjectApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UserSystemServiceInterface userSystemServiceInterface){
		return args -> {
			UserSystem userSystemCustomer = new UserSystem("customer", "customer");
			userSystemCustomer.setAuthorities(Arrays.asList(new Authority(CUSTOMER_ROLE)));
			userSystemServiceInterface.create(userSystemCustomer);
			UserSystem userSystemAdmin = new UserSystem("admin", "admin");
			userSystemAdmin.setAuthorities(Arrays.asList(new Authority(ADMIN_ROLE)));
			userSystemServiceInterface.create(userSystemAdmin);
		};
	}
}
