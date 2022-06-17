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

import com.ninjaone.backendinterviewproject.model.interfaces.OperatingSystemIdInterface;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "device_ninja_one")
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeviceNinjaOne implements AbstractEntity<Long>, OperatingSystemIdInterface {
    @Id
    @SequenceGenerator(
            name = "device_sequence",
            sequenceName = "device_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "device_sequence"
    )
    @Column(name = "device_id",
            updatable = false)
    private Long id;

    @NonNull
    @Column(name = "system_name")
    private String systemName;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private OperatingSystemType operatingSystemType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Customer customer;

    @Override
    public String getOperatingSystemId() {
        return getOperatingSystemType().getOperatingSystem().getId();
}

public DeviceNinjaOne(String systemName, String operatingSystemTypeId, String customerId) {
        this.systemName = systemName;
        this.operatingSystemType = new OperatingSystemType(operatingSystemTypeId);
        this.customer = new Customer(customerId);
}
}
