package com.ninjaone.backendinterviewproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {
    private Long deviceId;
    private String systemName;
    private String operatingSystemType;
}
