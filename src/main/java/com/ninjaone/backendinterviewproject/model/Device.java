package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ninjaone.backendinterviewproject.model.enums.DeviceType;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(unique = true, nullable = false)
    @NotNull
    private String systemName;

    @ManyToMany(mappedBy = "devicesServiced")
    private Set<ITService> linkedITServices = Collections.emptySet();

    public Device(long id, DeviceType type, String systemName, Set<ITService> linkedITServices) {
        this.id = id;
        this.type = type;
        this.systemName = systemName;
        this.linkedITServices = linkedITServices;
    }

    public Device() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Set<ITService> getLinkedServices() {
        return linkedITServices;
    }

    public void setLinkedServices(Set<ITService> linkedITServices) {
        this.linkedITServices = linkedITServices;
    }

    @PrePersist
    public void standardizeData() {
        systemNameToUpperCase();
    }


    private void systemNameToUpperCase() {
        if(this.systemName != null) {
            this.systemName = this.systemName.toUpperCase();
        }
    }
}
