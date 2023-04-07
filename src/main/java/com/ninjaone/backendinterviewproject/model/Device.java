package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"system_name", "device_type"})
})
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "system_name", nullable = false, length = 50, unique = true)
    private String systemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", nullable = false, length = 50)
    private DeviceType deviceType;

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RmmServiceExecution> rmmServiceExecutionList;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
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