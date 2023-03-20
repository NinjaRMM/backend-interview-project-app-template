package com.ninjaone.backendinterviewproject.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
/**
*  Simple cache that uses device id for key and cost as value.
*  This cache is created/updated on creation of device and then on
*  any changes with adding  or removing services.
* */
@Component
public class DeviceCostCache {
    private static final Logger LOGGER = Logger.getLogger(DeviceCostCache.class.getName());
    private Map<String, Double> deviceCostCache;
    public DeviceCostCache() {
        deviceCostCache = new HashMap<>();
    }

    /**
     * Updates the device cost cache with the specified cost for the device.
     *
     * @param deviceId the ID of the device to update the cache for
     * @param cost the cost to update the cache with
     */
    public void updateCache(String deviceId, Double cost) {
        LOGGER.info("Updating cache for device " + deviceId + " with cost " + cost);
        deviceCostCache.put(deviceId, cost);
    }

    /**
     * Removes the cost associated with the given device from the cache.
     *
     * @param deviceId the ID of the device to remove from the cache
     */
    public void removeFromCache(String deviceId) {
        LOGGER.info("Removing device " + deviceId);
        deviceCostCache.remove(deviceId);
    }

    /**
     * Removes the cost associated with the given device from the cache.
     *
     * @param deviceId the ID of the device to remove from the cache
     */
    public Double getFromCache(String deviceId) {
        Double cost = deviceCostCache.get(deviceId);
        if (cost != null) {
            LOGGER.info("Retrieving cost " + cost + " from cache for device " + deviceId);
        }
        return cost;
    }

    /**
     * Checks if the given device is in the cache.
     *
     * @param deviceId the ID of the device to check
     * @return true if the device is in the cache, false otherwise
     */
    public boolean inCache(String deviceId) {
        boolean inCache = deviceCostCache.containsKey(deviceId);
        LOGGER.info("Device " + deviceId + " in cache: " + inCache);
        return inCache;
    }

}