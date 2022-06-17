package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "operating_system_type")
@AllArgsConstructor
public class OperatingSystemType implements AbstractEntity<String> {

        public OperatingSystemType(String operatingSystemTypeId, String operatingSystemId) {
                this.id = operatingSystemTypeId;
                this.operatingSystem = new OperatingSystem(operatingSystemId);
        }

        public OperatingSystemType(String operatingSystemTypeId) {
                this.id = operatingSystemTypeId;
        }

        @NonNull
        @Id
        @Column(name = "operating_system_type_id")
        private String id;

        @NonNull
        @ManyToOne
        @JoinColumn(nullable = false)
        private OperatingSystem operatingSystem;
}
