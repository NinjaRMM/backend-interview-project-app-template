package com.ninjaone.backendinterviewproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendInterviewProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendInterviewProjectApplication.class, args);
    }
}
