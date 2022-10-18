package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;

/***
 * Entity that maps the pricing by device type and service (e.g.: MAC and windows AVs have different pricing)
 */
@Entity
public class ServiceDeviceType {

    @Id
    private String id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DeviceType deviceType;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="service__id",referencedColumnName="id", insertable=true, updatable=false)
    private ServiceOne serviceOne;
    private double serviceCost;

    public ServiceDeviceType(){}
    public ServiceDeviceType(String serviceId, String deviceId, double serviceCost) {
        this.setServiceCost(serviceCost);
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public ServiceOne getServiceOne() {
        return serviceOne;
    }

    public void setServiceOne(ServiceOne serviceOne) {
        this.serviceOne = serviceOne;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }
}
