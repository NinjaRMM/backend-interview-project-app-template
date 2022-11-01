package com.ninjaone.backendinterviewproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
    private int id;
    private String systemName;
    private DeviceTypeDto type;
    private List<ServiceDto> services;
}
