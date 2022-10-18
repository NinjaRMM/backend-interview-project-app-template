package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceOneRepository;
import com.ninjaone.backendinterviewproject.model.ServiceOne;
import com.ninjaone.backendinterviewproject.service.Interfaces.IServiceOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceOneService implements IServiceOneService {
    @Autowired
    private ServiceOneRepository ServiceOneRepository;

    public ServiceOneService(ServiceOneRepository ServiceOneRepository) {
        this.ServiceOneRepository = ServiceOneRepository;
    }

    @Override
    public ServiceOne saveServiceOneEntity(ServiceOne serviceOne){
        return ServiceOneRepository.save(serviceOne);
    }

    @Override
    public Optional<ServiceOne> getServiceOneEntity(String id){
        return ServiceOneRepository.findById(id);
    }
    @Override
    public void deleteServiceOneEntity(String id) {
        ServiceOneRepository.deleteById(id);
    }
}
