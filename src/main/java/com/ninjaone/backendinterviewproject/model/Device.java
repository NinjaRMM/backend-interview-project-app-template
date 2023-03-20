package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Device class represents a device in the system.
 * Each device has an id, system name, type, and list of services.
 */
@Entity
public class Device {

    /**
     * The device's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * The name for a system should be unique to every device.
     */
    @Column(unique = true)
    private String systemName;

    /**
     * The devices type.
     * Ex:Type (Windows Workstation, Windows Server, Mac, etc.)
     */
    private String type;

    /**
     * The List of Services
     * Antivirus, Backup, Screen share are examples,
     * a service can only be created with a default price
     */
    @JsonIgnoreProperties("devices")
    @ManyToMany
    private List<Service> services;

    /**
     * Creates a new instance of the Device class
     */
    public Device(){}


    /**
     * Creates a new instance of the Device class with the given properties.
     *
     * @param id The device's id.
     * @param systemName The device's system name which is unique.
     * @param type The device's type.
     */
    public Device(String id, String systemName, String type){
        this.id = id;
        this.systemName = systemName;
        this.type = type;
        this.services= new ArrayList<>();
    }


    /**
     * Returns the list of services associated with the device.
     *
     * @return The list of services.
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * Sets the list of services associated with the device.
     *
     * @param services The list of services.
     */
    public void setServices(List<Service> services) {
        this.services = services;
    }

    /**
     * Returns the device's system name.
     *
     * @return The system name.
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Sets the device's system name.
     *
     * @param systemName The system name.
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * Returns the device's unique identifier.
     *
     * @return The unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the device's unique identifier.
     *
     * @param id The unique identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the device's type.
     *
     * @return The device's type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the device's type.
     *
     * @param type The device's type.
     */
    public void setType(String type) {
        this.type = type;
    }

}
