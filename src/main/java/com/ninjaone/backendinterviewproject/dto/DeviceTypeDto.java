package com.ninjaone.backendinterviewproject.dto;

import com.ninjaone.backendinterviewproject.model.ServiceByDevice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTypeDto {

    private int id;
    private String type;

}
