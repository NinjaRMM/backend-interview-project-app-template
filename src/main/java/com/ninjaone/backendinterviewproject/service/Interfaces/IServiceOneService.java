package com.ninjaone.backendinterviewproject.service.Interfaces;

import com.ninjaone.backendinterviewproject.model.ServiceOne;

import java.util.Optional;

public interface IServiceOneService {
    ServiceOne saveServiceOneEntity(ServiceOne serviceOne);

    Optional<ServiceOne> getServiceOneEntity(String id);

    void deleteServiceOneEntity(String id);
}
