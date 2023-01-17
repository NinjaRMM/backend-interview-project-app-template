package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjaone.backendinterviewproject.model.enums.DeviceType;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ITService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    private String description;

    @NotNull
    private double cost;

    @ElementCollection(targetClass = DeviceType.class)
    @CollectionTable(name = "service_device_types", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "device_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<DeviceType> deviceAttributes = Collections.singleton(DeviceType.ANY);

    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(
            name= "devices_serviced",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    @JsonIgnore
    private Set<Device> devicesServiced = Collections.emptySet();


    public ITService() {
    }

    public ITService(Long id, String name, String description, double cost, Collection<DeviceType> deviceAttributes, Set<Device> devicesServiced) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.deviceAttributes = deviceAttributes;
        this.devicesServiced = devicesServiced;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Collection<DeviceType> getDeviceAttributes() {
        return deviceAttributes;
    }

    public void setDeviceAttributes(Collection<DeviceType> deviceAttributes) {
        this.deviceAttributes = deviceAttributes;
    }

    public Set<Device> getDevicesServiced() {
        return devicesServiced;
    }

    public void setDevicesServiced(Set<Device> devicesServiced) {
        this.devicesServiced = devicesServiced;
    }

    @PrePersist
    public void standardizeData() {
        if(this.name != null) {
            this.name = this.name.toUpperCase();
        }
    }
}
