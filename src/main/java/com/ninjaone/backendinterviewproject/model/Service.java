package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a service offered by the application.
 * Using the /services post endpoint you can create more services
 * Note endpoint will require a default price in price object
 */
@Entity
public class Service {
    /**
     * The Service ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * The name of service the app offers
     * Name must be unique to create
     */
    @Column(unique = true)
    private String name;

    /**
     * A map of prices for the service, keyed by operating system type.
     * Will attemp to define the type based on device type containing
     *  MAC or WINDOWS
     */
    @ElementCollection(targetClass = Double.class)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<OSType, Double> prices;


    /**
     * The List of DEVICES
     * Services should know what services they are attached too
     */
    @JsonIgnoreProperties("services")
    @ManyToMany(mappedBy = "services")
    private List<Device> devices;

    /**
     * Creates a new instance of the {@code Service} class.
     */
    public Service() {}

    /**
     * Creates a new instance of the {@code Service} class with the specified parameters.
     *
     * @param id     The unique ID of the service.
     * @param name   The name of the service.
     * @param prices A map of prices for the service, keyed by operating system type.
     */
    public Service(String id, String name, Map<OSType, Double> prices) {
        this.id = id;
        this.name = name;
        this.prices = prices;
        this.devices = new ArrayList<>();
    }



/**
 * Used to create service with all params
 * Used to create copies for calculations
 * */
    public Service(Service service) {
        this.id = service.id;
        this.name = service.name;
        this.prices = service.prices;
        this.devices = service.devices;
    }


    /**
     * Returns the list of services associated with the device.
     *
     * @return The list of services.
     */
    public List<Device> getDevices() {
        return this.devices;
    }

    /**
     * Sets the list of services associated with the device.
     *
     * @param devices The list of services.
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    /**
     * Returns the name of the service.
     *
     * @return The name of the service.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the service.
     *
     * @param name The name of the service.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the unique ID of the service.
     *
     * @return The unique ID of the service.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique ID of the service.
     *
     * @param id The unique ID of the service.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns a map of prices for the service, keyed by operating system type.
     *
     * @return A map of prices for the service, keyed by operating system type.
     */
    public Map<OSType, Double> getPrices() {
        return prices;
    }

    /**
     * Sets the map of prices for the service, keyed by operating system type.
     *
     * @param prices A map of prices for the service, keyed by operating system type.
     */
    public void setPrices(Map<OSType, Double> prices) {
        this.prices = prices;
    }

    /**
     * Represents the operating system types for which the service is available.
     * TODO:Need to add mor operating systems here when supported
     */
    public enum OSType {
        /**
         * Represents the Mac operating system type.
         */
        MAC,

        /**
         * Represents the Windows operating system type.
         */
        WINDOWS,

        /**
         * Represents the default operating system type.
         * Each service will have default price
         */
        DEFAULT,
    }
}
