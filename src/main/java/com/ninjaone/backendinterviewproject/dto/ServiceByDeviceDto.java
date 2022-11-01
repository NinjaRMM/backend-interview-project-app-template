package com.ninjaone.backendinterviewproject.dto;

import com.ninjaone.backendinterviewproject.model.ServiceByDevice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceByDeviceDto {

    private int id;
    private String name;
    private List<DeviceDto> devices;

}
