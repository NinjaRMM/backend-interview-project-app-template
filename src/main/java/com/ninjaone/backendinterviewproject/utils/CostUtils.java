package com.ninjaone.backendinterviewproject.utils;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;

import java.util.List;
import java.util.Map;

/**
 *  Cost calculations methods
 */
public class CostUtils {

    /**
     * Calculates device price based on services in device object
     * add default price of 4  and then iterates through services
     * and adds price based on device type, Currently only WINDOWS and MAC
     * are supported
     * @param device device to calculate pricing
     * */
    public static double calculateTotalDeviceCost(Device device) {
        double totalCost = 0;
        // Device cost is 4 standard
        totalCost+=4;
        // See if there are services
        List<Service> services = device.getServices();
        if (services == null || services.isEmpty()) {
            // return price of 4
            return  totalCost;
        }

        // Grab all services and calculate pricing
        for (Service service : device.getServices()) {
        totalCost+= getPrice(device.getType(),service);
        }
        return totalCost;
    }

    /**
     * Calculates device price based on service object based on
     * deviceType
     * @param deviceType device type of device object
     * @param service service object contain pricing information
     * */
    public static double getPrice(String deviceType, Service service) {
        Map<Service.OSType, Double> priceMapping = service.getPrices();
        // check if there is OS override
        //  if override does not exist or can not be determined return default
        Service.OSType osType = getOSTypeForDeviceType(deviceType);

        // check if mapping exist
         if(priceMapping.containsKey(osType)) {
             // return price
             return priceMapping.get(osType);
         }
        // Since all services will have a default return default
        return service.getPrices().get(Service.OSType.DEFAULT);
    }

    /**
     * Basic method to check if os type is mentioned in device type
     * if both are mention just take default price
     * @param deviceType
     * @return ostype ie: WINDOWS,MAC,DEFAULT
     */
    public static Service.OSType getOSTypeForDeviceType(String deviceType) {

        // check if there is OS override
        boolean macOs = deviceType.toUpperCase().contains(Service.OSType.MAC.toString());
        boolean windowsOS = deviceType.toUpperCase().contains(Service.OSType.WINDOWS.toString());

        if( macOs && windowsOS){
            // If we can not determine by the name use default pricing
            return Service.OSType.DEFAULT;
        }
        if(macOs){
            // assume we have a mac device and use mac overrides
            return Service.OSType.MAC;
        } else if (windowsOS) {
            // assume it is windows
            return Service.OSType.WINDOWS;
        }
        // return default if no conditions are met
        return Service.OSType.DEFAULT;
    }
}


