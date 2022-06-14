package com.ninjaone.backendinterviewproject.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "device_ninja_one")
public class DeviceNinjaOne implements AbstractEntity<Long>  {
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

    @Column(name = "system_name")
    private String systemName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OperatingSystemType operatingSystemType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customerOwner;
}
