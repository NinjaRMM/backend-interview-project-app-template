package com.ninjaone.backendinterviewproject.model;

import java.util.List;
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

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Customer implements AbstractEntity<String> {

    @NonNull
    @Id
    @Column(name = "customer_id")
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<DeviceNinjaOne> devices;

    @ManyToMany
    @JoinTable(name = "customer_service", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<ServiceNinjaOne> services;
}
