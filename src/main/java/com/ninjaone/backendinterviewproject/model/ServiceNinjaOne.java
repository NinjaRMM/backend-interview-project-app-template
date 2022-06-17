package com.ninjaone.backendinterviewproject.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

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

import com.ninjaone.backendinterviewproject.model.interfaces.OperatingSystemIdInterface;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "service_ninja_one")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ServiceNinjaOne implements AbstractEntity<Long>, OperatingSystemIdInterface {
        @Id
        @SequenceGenerator(name = "service_sequence", sequenceName = "service_sequence", allocationSize = 1)
        @GeneratedValue(strategy = SEQUENCE, generator = "service_sequence")
        @Column(name = "service_id", updatable = false)
        private Long id;

        @NonNull
        @Column(name = "service_name")
        private String serviceName;

        @NonNull
        @ManyToOne
        @JoinColumn(name = "operating_system_id", nullable = false)
        private OperatingSystem operatingSystem;

        @Column(name = "service_price")
        private double servicePrice;

        @ManyToMany
        @JoinTable(name = "customer_service", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
        private List<Customer> customers;

        @Override
        public String getOperatingSystemId() {
                return operatingSystem.getId();
        }

        public ServiceNinjaOne(String serviceName, String operatingSystemId, double servicePrice) {
                this.serviceName = serviceName;
                this.operatingSystem = new OperatingSystem(operatingSystemId);
                this.servicePrice = servicePrice;
        }

        public ServiceNinjaOne(Long id, String serviceName, String operatingSystemId, double servicePrice) {
                this.id = id;
                this.serviceName = serviceName;
                this.operatingSystem = new OperatingSystem(operatingSystemId);
                this.servicePrice = servicePrice;
        }

}
