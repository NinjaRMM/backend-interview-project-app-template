package com.ninjaone.backendinterviewproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceDTO {
    private Long serviceId;
    private String serviceName;
    private double servicePrice;
}
