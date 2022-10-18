package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer {
    @Id
    private String id;
    private String customerName;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Device> device;

    public Customer(){}
    public Customer(String id, String customerName, Set<Device> device) {
        this.id = id;
        this.customerName = customerName;
        this.device = device;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Set<Device> getDevice() {
        return device;
    }

    public void setDevice(Set<Device> device) {
        this.device = device;
    }
}
