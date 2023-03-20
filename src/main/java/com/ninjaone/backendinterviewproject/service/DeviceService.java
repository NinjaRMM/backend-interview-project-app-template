package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**

 This class provides a service layer for managing devices.
 Adding, Deleting , and retrieval
 */
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    /**

     Constructor for the DeviceService class.
     @param deviceRepository the repository for Device entities
     */
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    /**

     Saves a Device entity to the database.
     @param device the Device entity to save
     @return the saved Device entity
     */
    public Device saveDeviceEntity(Device device){
        return deviceRepository.save(device);
    }

    /**
     Retrieves a Device entity from the database by its id.
     @param id the id of the Device entity to retrieve
     @return an Optional containing the retrieved Device entity, or an empty Optional if the entity was not found
     */
    public Optional<Device> getDeviceEntity(String id){
        return deviceRepository.findById(id);
    }

    /**
     Retrieves all Device entities from the database.
     @return an Iterable containing all Device entities in the database
     */
    public Iterable<Device> getAllDevices(){
        return deviceRepository.findAll();
    }
    /**
     Deletes a Device entity from the database by its id.
     @param id the id of the Device entity to delete
     */
    public void deleteDeviceEntity(String id) {
        deviceRepository.deleteById(id);
    }
}