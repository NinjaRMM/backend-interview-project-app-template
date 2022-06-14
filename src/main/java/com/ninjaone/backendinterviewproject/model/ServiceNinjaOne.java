package com.ninjaone.backendinterviewproject.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "service_ninja_one")
public class ServiceNinjaOne implements AbstractEntity<ServiceId> {
    @EmbeddedId
    ServiceId id;

    @Column(name = "service_price")
    private BigDecimal servicePrice;

}
