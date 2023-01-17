package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ITServiceRepository;
import com.ninjaone.backendinterviewproject.model.ITService;
import org.springframework.stereotype.Service;

@Service
public class ITServiceService {


    private final ITServiceRepository serviceRepository;

    public ITServiceService(ITServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ITService saveService(ITService service) {
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

}
