
package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesService {
    private final ServiceRepository serviceRepository;

    /**
     * Constructs a new instance of the ServicesService class with the given
     * ServiceRepository.
     *
     * @param serviceRepository the ServiceRepository to use for data access
     */
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * Returns an iterable collection of all Service entities in the ServiceRepository.
     *
     * @return an iterable collection of all Service entities in the ServiceRepository
     */
    public Iterable<com.ninjaone.backendinterviewproject.model.Service> getAllServices(){
        return  serviceRepository.findAll();
    }

    /**
     * Saves the given Service entity to the ServiceRepository.
     *
     * @param service the Service entity to save
     * @return the saved Service entity
     */
    public com.ninjaone.backendinterviewproject.model.Service saveServiceEntity(com.ninjaone.backendinterviewproject.model.Service service){
        return serviceRepository.save(service);
    }

    /**
     * Returns an optional containing the Service entity with the given ID, if it
     * exists in the ServiceRepository.
     *
     * @param id the ID of the Service entity to retrieve
     * @return an optional containing the Service entity with the given ID, if it
     *         exists in the ServiceRepository
     */
    public Optional<com.ninjaone.backendinterviewproject.model.Service> getServiceEntity(String id){
        return  serviceRepository.findById(id);
    }

    /**
     * Deletes the Service entity with the given ID from the ServiceRepository.
     *
     * @param id the ID of the Service entity to delete
     */
    public void deleteServiceEntity(String id) {
        serviceRepository.deleteById(id);
    }
}