package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.cache.DeviceCostCache;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServicesService;
import com.ninjaone.backendinterviewproject.utils.CostUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
/**
 * Endpoints to create delete and retrieve services
 *
 * */
@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private DeviceService deviceService;


    private  final ServicesService servicesService;

    @Autowired
    private DeviceCostCache deviceCostCache;

    public ServiceController(ServicesService servicesService ) {
        this.servicesService = servicesService;
    }
    /**
     * Creates service based on json in request body
     * TODO: More checks on service object passed in
     * @param service service object to create
     * */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity postDeviceEntity(@RequestBody Service service){
        try {
            if(service.getName() == null || service.getName().isBlank()){
                throw new IllegalArgumentException("Name is required to create service");
            }
            if(service.getPrices() == null){
                throw new IllegalArgumentException("Prices are required to create service: " + service.getName());
            }
            if (!service.getPrices().containsKey(Service.OSType.DEFAULT)){
                throw new IllegalArgumentException("DEFAULT  price is required for service: " + service.getName());
            }
            Service savedService = servicesService.saveServiceEntity(service);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Name for service not allowed");
        }
    }

    /**
     * @returns All Services in DB
     * */
    @GetMapping()
    private Iterable<Service> getALLServices(){
        return servicesService.getAllServices();
    }

    /**
     * Path provides id
     * @returns Service by ID
     * */
    @GetMapping("/{serviceId}")
    private ResponseEntity getServiceEntity(@PathVariable String serviceId){
        try {
           Service service = servicesService.getServiceEntity(serviceId)
                    .orElseThrow();
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Service",service));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: Serviceid: " + serviceId + " does not exist");
        }
    }

    /**
     * Removes service form all devices referencing it
     * will delete service by id in DB. Will update cache as price will change
     * */
    @DeleteMapping("/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteServiceEntity(@PathVariable String serviceId) throws Exception {
        try {
            // See if service exist
            Optional<Service> optionalService = servicesService.getServiceEntity(serviceId);

            if(!optionalService.isPresent()){
                throw new Exception("Service does not exist");
            }
            // Get Service object
            Service service = optionalService.get();
            // Get all devices associated with service
            List<Device> devices = service.getDevices();
            for (Device device:devices){
                device.getServices().remove(service);
                deviceService.saveDeviceEntity(device);
                if(deviceCostCache.inCache(device.getId())){
                    // Just need to subtract from what's in cache with service being removed
                    deviceCostCache.updateCache(device.getId(),deviceCostCache.getFromCache(device.getId()) - CostUtils.getPrice(device.getType(),service));
                } else  {
                    // if not in cache we need to update cache to contain the service
                    deviceCostCache.updateCache(device.getId(),CostUtils.calculateTotalDeviceCost(device));
                }
            }
            // Delete the service
           servicesService.deleteServiceEntity(serviceId);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /***
     * Get service price
     * This can be used to get price and programmatically change
     * by calling the put endpoint with edited payload
     * */
    @GetMapping("/{serviceId}/prices")
    public ResponseEntity<Map<Service.OSType, Double>> getServicePrice(@PathVariable String serviceId) {
        try {
            Optional<Service> optionalService = servicesService.getServiceEntity(serviceId);
            if (optionalService.isPresent()) {
                return  ResponseEntity.ok().body(optionalService.get().getPrices());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update existing service prices
     * enter a new price payload and edit the price
     * This endpoint will also update the cache
     * */
    @PutMapping("/{serviceId}/prices")
    public ResponseEntity updateServicePrice(@PathVariable String serviceId, @RequestBody Map<Service.OSType, Double> prices) {
        try {
            Optional<Service> optionalService = servicesService.getServiceEntity(serviceId);
            if (optionalService.isPresent()) {
                if(prices == null || prices.isEmpty()){
                    throw new IllegalArgumentException("Payload is empty or not correctly created");
                }
                if (!prices.containsKey(Service.OSType.DEFAULT)){
                    throw new IllegalArgumentException("DEFAULT  price is required for prices");
                }
                Service service = optionalService.get();
                Service servicePriceUpdate = new Service(service);

                servicePriceUpdate.setPrices(prices);
                Iterable<Device> devices = service.getDevices();
                for (Device device:devices ) {
                    double oldCostForService = CostUtils.getPrice(device.getType(),service);
                    double newCostForService = CostUtils.getPrice(device.getType(),servicePriceUpdate);
                    if(oldCostForService != newCostForService) {
                        if (deviceCostCache.inCache(device.getId())){
                            // get cache price
                            double cacheCost = deviceCostCache.getFromCache(device.getId());
                            // remove old cost from device
                            cacheCost -= oldCostForService;
                            // add new cost
                            cacheCost += newCostForService;
                            // update the cache
                            deviceCostCache.updateCache(device.getId(),cacheCost);
                        } else {
                            // Get cost of old device
                            double deviceCost = CostUtils.calculateTotalDeviceCost(device);
                            // remove old price
                            deviceCost -= oldCostForService;
                            // add new price
                            deviceCost += newCostForService;
                            // save in cache
                            deviceCostCache.updateCache(device.getId(),deviceCost);
                        }
                    } else {
                        // price did not change so check if in cache and update
                        if (!deviceCostCache.inCache(device.getId())) {
                            deviceCostCache.updateCache(device.getId(), CostUtils.calculateTotalDeviceCost(device));
                        }
                    }
                }
                servicesService.saveServiceEntity(servicePriceUpdate);
                return  ResponseEntity.ok().body(prices);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
