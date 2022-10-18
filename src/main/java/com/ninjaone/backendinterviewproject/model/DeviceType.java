package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class DeviceType {
    @Id
    private String id;
    private String deviceTypeName;

    public DeviceType(){}
    public DeviceType(String id, String deviceTypeName) {
        this.id = id;
        this.deviceTypeName = deviceTypeName;
    }

    public String getId() {
        return id;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }
}
