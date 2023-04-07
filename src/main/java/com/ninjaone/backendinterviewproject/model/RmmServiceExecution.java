package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "rmm_service_execution")
public class RmmServiceExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rmm_service_id", nullable = false)
    private RmmService rmmService;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "execution_date_time", nullable = false)
    private LocalDateTime executionDateTime = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public RmmService getRmmService() {
        return rmmService;
    }

    public void setRmmService(RmmService rmmService) {
        this.rmmService = rmmService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}