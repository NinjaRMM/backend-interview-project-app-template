package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rmm_service", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "device_type"})
})
public class RmmService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cost", nullable = false)
    private Float cost;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", nullable = false, length = 50)
    private DeviceType deviceType;

    @OneToMany(mappedBy = "rmmService", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RmmServiceExecution> rmmServiceExecutionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}