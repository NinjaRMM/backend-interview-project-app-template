package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "operating_system_type")
@NoArgsConstructor
public class OperatingSystemType implements AbstractEntity<String> {

        @NonNull
        @Id
        @Column(name = "operating_system_type_id")
        @JsonProperty("id")
        private String id;

        @NonNull
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(nullable = false)
        @JsonIgnore
        private OperatingSystem operatingSystem;
        
        public OperatingSystemType(String operatingSystemTypeId, String operatingSystemId) {
                this.id = operatingSystemTypeId;
                this.operatingSystem = new OperatingSystem(operatingSystemId);
        }

        public OperatingSystemType(String operatingSystemTypeId) {
                this.id = operatingSystemTypeId;
        }
}
