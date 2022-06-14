package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "operating_system_type")
public class OperatingSystemType implements AbstractEntity<String> {

        @Id
        @Column(name = "operating_system_type")
        private String id;
}
