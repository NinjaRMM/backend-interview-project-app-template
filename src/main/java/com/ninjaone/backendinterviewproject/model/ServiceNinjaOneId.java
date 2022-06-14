package com.ninjaone.backendinterviewproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ServiceNinjaOneId implements Serializable {

    @Column(name = "service_name")
    private String serviceName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OperatingSystem operatingSystem;
}
