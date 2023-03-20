package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.cache.DeviceCostCache;
import com.ninjaone.backendinterviewproject.utils.CostUtils;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private DeviceCostCache deviceCostCache;
    private static final Logger LOGGER = Logger.getLogger(DeviceController.class.getName());

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    /**
     * Creates device based on json in request body
     * Will also create cache pricing for object
     * TODO: More checks on Device object passed in
     * TODO: Allow for multiple devices  to be created at once
     * @param device device object that will be created
     * */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity postDeviceEntity(@RequestBody Device device) {

        try {
            if(device.getSystemName() == null || device.getSystemName().isBlank()){
                throw new IllegalArgumentException("systemName is required to create Device");
            }
            if(device.getType() == null || device.getType().isBlank()){
                throw new IllegalArgumentException("Type is required to create Device");
            }
            Device deviceCreated = deviceService.saveDeviceEntity(device);
            // Let create cache now so in the future if we want to create
            // devices with services on creation we can use this same endpoint
            deviceCostCache.updateCache(deviceCreated.getId(),CostUtils.calculateTotalDeviceCost(deviceCreated));
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Device Created",deviceCreated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Bad Request",e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate systemName not allowed for devices");
        }
    }

    /**
     * Gets device object by class
     * @param deviceId identifier of device object
     * @return
     */
    @GetMapping(value = "/{deviceId}")
    private ResponseEntity getDeviceEntity(@PathVariable String deviceId) {
        try {
            Device device = deviceService.getDeviceEntity(deviceId).orElseThrow();
            // When getting a device check and see if its in the cache
            if(!deviceCostCache.inCache(deviceId)) {
                // update cache if it is not there
                deviceCostCache.updateCache(deviceId,CostUtils.calculateTotalDeviceCost(device));
            }
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("Device:",device));
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: DeviceId: " + deviceId + " does not exist");
        }
    }

    /**
     * Get all device objects in the DB
     * @return
     */
    @GetMapping()
    private Iterable<Device> getALLDevices() {
        // Grab all devices
        Iterable<Device> devices = deviceService.getAllDevices();
        for(Device device: devices) {
            // check if cache is there
            if(!deviceCostCache.inCache(device.getId())){
                // update cache if it is not there
                deviceCostCache.updateCache(device.getId(), CostUtils.calculateTotalDeviceCost(device));
            }
        }
        return deviceService.getAllDevices();
    }


    /** *
     * Calculate cost of specified device id
     * @param deviceId id of device object
     */
    @GetMapping("/{deviceId}/cost")
    private ResponseEntity getDeviceCost(@PathVariable String deviceId) {

        try {
            // check if cache has device
            if (deviceCostCache.inCache(deviceId)) {
                LOGGER.info("Using cache to get cost");
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Total cost", deviceCostCache.getFromCache(deviceId)));
            }
            // Check if device exist
            Optional<Device> optionalDevice = deviceService.getDeviceEntity(deviceId);

            if (!optionalDevice.isPresent()) {
                throw new IllegalArgumentException("Device does not exist");
            }
            // Grab device
            Device device = optionalDevice.get();
            LOGGER.info("Calculating using db");
            // Calculate cost
            double totalCost = CostUtils.calculateTotalDeviceCost(device);
            // Store cost in cache
            deviceCostCache.updateCache(deviceId,totalCost);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(Map.of("Total cost", totalCost));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Bad Request", e.getMessage()));
        }
    }


    /**
     * Calculate cost of all device object in the DB
     * cache will be updated if cost
     * */
    @GetMapping("/cost")
    private ResponseEntity<Map<String, Double>> getAllDeviceCost() {

        try {
            // Grab all devices
            Iterable<Device> devices = deviceService.getAllDevices();

            // Initialize total cost
            double totalCost= 0.0;
            for(Device device: devices){
                if(deviceCostCache.inCache(device.getId())){
                    // Add cost of device in cache to total
                    totalCost+= deviceCostCache.getFromCache(device.getId());

                } else {
                    // when application restarts the cache is gone
                    // The DB still exist, so we will recreate the cache
                    double cost = CostUtils.calculateTotalDeviceCost(device);
                    totalCost+= cost;
                    // Recreate cache
                    deviceCostCache.updateCache(device.getId(),cost);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("Total cost", totalCost));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Device found");
        }
    }


    /**
     * Delete device by id
     * Remove device id key from cache
     * */
    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDeviceEntity(@PathVariable String deviceId) {
        try {
            deviceService.deleteDeviceEntity(deviceId);
            if (deviceCostCache.inCache(deviceId)) {
                deviceCostCache.removeFromCache(deviceId);
            }
        } catch (Exception e) {
           throw e;
        }
    }


/**
 * Add service to device object
 * Will update cache as price will change
 *
 * */
    @PostMapping(value = "/{deviceId}/service/{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity  addServiceToDevice(@PathVariable("deviceId") String deviceId,
                                      @PathVariable("serviceId") String serviceId){
        try {
            // First lets check if device exist
            // Check if the device exists
            Optional<Device> optionalDevice = deviceService.getDeviceEntity(deviceId);

            if (!optionalDevice.isPresent()) {
                throw new IllegalArgumentException("Device does not exist");
            }
            // Now we get device to add service
            Device device = optionalDevice.get();

            // Check if service exist
            Optional<Service> optionalService = servicesService.getServiceEntity(serviceId);

            if(!optionalService.isPresent()){
                throw new IllegalArgumentException("Service does not exist");
            }
            Service service = optionalService.get();
            // Now we check if  service is already applied to  device
            if(device.getServices().contains(service)){
                // service is already applied just return device
                if(!deviceCostCache.inCache(device.getId())){
                    // if it is not in the cache update
                    deviceCostCache.updateCache(deviceId, CostUtils.calculateTotalDeviceCost(device));
                }

                return  ResponseEntity.status(HttpStatus.OK).body(device);
            }
            // Add service to device
            device.getServices().add(service);
            // Add Device to service
            service.getDevices().add(device);
            // update cost cached after adding service
            // if it already exists in cache just cost for new service to existing cache
            if(deviceCostCache.inCache(device.getId())){
                deviceCostCache.updateCache(deviceId, deviceCostCache.getFromCache(deviceId)+CostUtils.getPrice(device.getType(),service));
            } else {
                // if it is not in the cache up with total calculation as it needs default 4$
                deviceCostCache.updateCache(deviceId, CostUtils.calculateTotalDeviceCost(device));
            }
            servicesService.saveServiceEntity(service);
            device = deviceService.saveDeviceEntity(device);

            return  ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(device);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Remove service to device object
     * Will update cache as price will change
     *
     * */
    @DeleteMapping("/{deviceId}/service/{serviceId}")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity  removeServiceToDevice(@PathVariable("deviceId") String deviceId,
                                               @PathVariable("serviceId") String serviceId){

        try {
            // First lets check if device exist
            // Check if the device exists
            Optional<Device> optionalDevice = deviceService.getDeviceEntity(deviceId);

            if (!optionalDevice.isPresent()) {
                throw new IllegalArgumentException("Device does not exist");
            }
            // Now we get device to add service
            Device device = optionalDevice.get();

            // Check if service exist
            Optional<Service> optionalService = servicesService.getServiceEntity(serviceId);

            if (!optionalService.isPresent()) {
                throw new IllegalArgumentException("Service does not exist");
            }
            Service service = optionalService.get();
            // Now we check if  service is part of device
            if (!device.getServices().contains(service)) {
                // check if device is in cache update cache if it is not
                if (!deviceCostCache.inCache(device.getId())) {
                    deviceCostCache.updateCache(deviceId, CostUtils.calculateTotalDeviceCost(device));
                }
                return  ResponseEntity.status(HttpStatus.CREATED).body(device);
            }


            // Remove service from device
            device.getServices().remove(service);
            // Remove device from service
            service.getDevices().remove(device);
            // update cost cached after removing service
            // if it already exists in cache just cost remove cost
            if (deviceCostCache.inCache(device.getId())) {
                deviceCostCache.updateCache(deviceId, deviceCostCache.getFromCache(deviceId) - CostUtils.getPrice(device.getType(),service));
            } else {
                // if it is not in the cache up with total calculation as it needs default 4$
                deviceCostCache.updateCache(deviceId, CostUtils.calculateTotalDeviceCost(device));
            }
            servicesService.saveServiceEntity(service);
            device = deviceService.saveDeviceEntity(device);
            return  ResponseEntity.status(HttpStatus.CREATED).body(device);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
