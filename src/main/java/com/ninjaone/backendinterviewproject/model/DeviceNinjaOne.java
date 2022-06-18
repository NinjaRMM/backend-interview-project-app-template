package com.ninjaone.backendinterviewproject.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ninjaone.backendinterviewproject.model.interfaces.OperatingSystemIdInterface;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "device_ninja_one")
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DeviceNinjaOne implements AbstractEntity<Long>, OperatingSystemIdInterface {
        @Id
        @SequenceGenerator(name = "device_sequence", sequenceName = "device_sequence", allocationSize = 1)
        @GeneratedValue(strategy = SEQUENCE, generator = "device_sequence")
        @Column(name = "device_id", updatable = false)
        @JsonProperty("id")
        private Long id;

        @NonNull
        @Column(name = "system_name")
        @JsonProperty("systemName")
        private String systemName;

        @NonNull
        @ManyToOne
        @JoinColumn(nullable = false)
        @JsonProperty("operatingSystemType")
        private OperatingSystemType operatingSystemType;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "customer_id", nullable = false, updatable = false)
        @JsonProperty("customer")
        private Customer customer;

        @Override
        @JsonIgnore
        public String getOperatingSystemId() {
                if (getOperatingSystemType().getOperatingSystem() != null)
                        return getOperatingSystemType().getOperatingSystem().getId();

                return "";
        }

        public DeviceNinjaOne(String systemName, String operatingSystemTypeId, String customerId) {
                this.systemName = systemName;
                this.operatingSystemType = new OperatingSystemType(operatingSystemTypeId);
                this.customer = new Customer(customerId);
        }

        public DeviceNinjaOne(String systemName, String operatingSystemTypeId, String operatingSystemId,
                        String customerId) {
                this.systemName = systemName;
                this.operatingSystemType = new OperatingSystemType(operatingSystemTypeId, operatingSystemId);
                this.customer = new Customer(customerId);
        }
}
