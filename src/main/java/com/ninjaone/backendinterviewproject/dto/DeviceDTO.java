package com.ninjaone.backendinterviewproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
    @JsonProperty("deviceId")
    private Long deviceId;
    @JsonProperty("systemName")
    private String systemName;
    @JsonProperty("operatingSystemType")
    private String operatingSystemType;
}
