package com.ninjaone.backendinterviewproject.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "service_ninja_one")
public class ServiceNinjaOne implements AbstractEntity<Long> {
        @Id
        @SequenceGenerator(
                name = "service_sequence",
                sequenceName = "service_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "service_sequence"
        )
        @Column(name = "service_id",
                updatable = false)
        private Long id;

        @Column(name = "service_name")
        private String serviceName;
    
        @ManyToOne
        @JoinColumn(name = "operating_system_id", nullable = false)
        private OperatingSystem operatingSystem;

        @Column(name = "service_price")
        private BigDecimal servicePrice;

        @ManyToMany
        @JoinTable(
          name = "customer_service", 
          joinColumns = @JoinColumn(name = "service_id"), 
          inverseJoinColumns = @JoinColumn(name = "customer_id"))
        private Set<Customer> customers;

}
