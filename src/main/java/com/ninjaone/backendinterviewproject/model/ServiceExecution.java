package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;

@Entity
@Table(name = "service_execution")
public class ServiceExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}