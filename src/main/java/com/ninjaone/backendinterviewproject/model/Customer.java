package com.ninjaone.backendinterviewproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @Column(name = "username")
    private String id;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customerOwner", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<ServiceNinjaOne> devices = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_service",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_name"))
    private Set<ServiceNinjaOne> services = new HashSet<>();
}
