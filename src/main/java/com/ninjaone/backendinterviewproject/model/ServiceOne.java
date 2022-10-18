package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceOne {
    @Id
    private String id;
    private String serviceName;

    public ServiceOne(){}
    public ServiceOne(String id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    public String getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

}
