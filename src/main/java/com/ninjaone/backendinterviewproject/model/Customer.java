package com.ninjaone.backendinterviewproject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer implements AbstractEntity<String> {

    @Id
    @Column(name = "customer_id")
    private final String id;

    @OneToMany(mappedBy = "customerOwner", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Set<DeviceNinjaOne> devices;

    @ManyToMany
    @JoinTable(
      name = "customer_service", 
      joinColumns = @JoinColumn(name = "customer_id"), 
      inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<ServiceNinjaOne> services;


    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.devices = builder.devices;
        this.services = builder.services;
    }

    public static class CustomerBuilder {
        private final String id;
        private Set<DeviceNinjaOne> devices;
        private Set<ServiceNinjaOne> services;

        public CustomerBuilder(String id) {
            this.id = id;
        }

        public CustomerBuilder devices(Set<DeviceNinjaOne> devices) {
            this.devices = devices;
            return this;
        }

        public CustomerBuilder services(Set<ServiceNinjaOne> services) {
            this.services = services;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
