package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/***
 * Entity that records each one of services sold to a client´s device (e.g.: 4x services for a Mac device)
 */
@Entity
public class DeviceServiceDeviceType {
    @Id
    private String id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="service_device_type_id",referencedColumnName="id", insertable=true, updatable=false)
    private ServiceDeviceType serviceDeviceType;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="device_id",referencedColumnName="id", insertable=true, updatable=false)
    @JsonBackReference
    private Device device;

    public DeviceServiceDeviceType(){}
    public DeviceServiceDeviceType(String id, String value) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public ServiceDeviceType getServiceDeviceType() {
        return serviceDeviceType;
    }

    public void setServiceDeviceType(ServiceDeviceType serviceDeviceType) {
        this.serviceDeviceType = serviceDeviceType;
    }
}
