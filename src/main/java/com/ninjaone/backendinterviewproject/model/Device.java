package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String deviceName;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id",referencedColumnName="id", insertable=true, updatable=false)
    @JsonBackReference
    private Customer customer;
    private String deviceTypeId;

    public Device() { }

    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<DeviceServiceDeviceType> deviceServiceDeviceType;

    public Device(Long id, String deviceName,Customer customer, String deviceTypeId) {
        this.id = id;
        this.deviceName = deviceName;
        this.customer = customer;
        this.deviceTypeId = deviceTypeId;
    }

    public Device(String deviceName,Customer customer, String deviceTypeId) {
        this(null, deviceName, customer, deviceTypeId);
    }


    public String getDeviceName() {
        return deviceName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<DeviceServiceDeviceType> getDeviceServiceDeviceType() {
        return deviceServiceDeviceType;
    }

    public void setDeviceServiceDeviceType(Set<DeviceServiceDeviceType> deviceServiceDeviceType) {
        this.deviceServiceDeviceType = deviceServiceDeviceType;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
}
