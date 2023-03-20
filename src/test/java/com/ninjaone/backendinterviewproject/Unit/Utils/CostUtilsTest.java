package com.ninjaone.backendinterviewproject.Unit.Utils;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.model.Service.OSType;
import com.ninjaone.backendinterviewproject.utils.CostUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostUtilsTest {


/**
 * Default price is 4
 * Device with no service is 4.0
 * */
    @Test
    public void testCalculateTotalDeviceCostWithNoServices() {
        double expectedCost = 4;
        Device device = new Device();
        device.setType("Windows workstation");
        double actualCost = CostUtils.calculateTotalDeviceCost(device);
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testCalculateTotalDeviceCostWithServices() {
        double expectedCost = 26;
        Device device = new Device();
        device.setServices(new ArrayList<>());
        device.setType("Mac desktop");
        Service service1 = new Service();
        Map<OSType, Double> prices1 = new HashMap<>();
        prices1.put(OSType.MAC, 7.0);
        prices1.put(OSType.WINDOWS, 5.0);
        prices1.put(OSType.DEFAULT, 6.0);
        service1.setPrices(prices1);
        Service service2 = new Service();
        Map<OSType, Double> prices2 = new HashMap<>();
        prices2.put(OSType.DEFAULT, 15.0);
        service2.setPrices(prices2);
        device.getServices().add(service1);
        device.getServices().add(service2);
        double actualCost = CostUtils.calculateTotalDeviceCost(device);
        assertEquals(expectedCost, actualCost);
    }


    @Test
    public void testGetPriceWithoutOverride() {
        double expectedPrice = 5.0;
        Service service = new Service();
        Map<OSType, Double> prices = new HashMap<>();
        prices.put(OSType.DEFAULT, 5.0);
        service.setPrices(prices);
        double actualPrice = CostUtils.getPrice("Windows workstation", service);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetPriceWithOSOverride() {
        double expectedPrice = 7.0;
        Service service = new Service();
        Map<OSType, Double> prices = new HashMap<>();
        prices.put(OSType.MAC, 7.0);
        prices.put(OSType.WINDOWS, 15.0);
        prices.put(OSType.DEFAULT, 5.0);
        service.setPrices(prices);
        double actualPrice = CostUtils.getPrice(OSType.MAC.toString(), service);
        assertEquals(expectedPrice, actualPrice);
    }
    @Test
    public void testGetOSTypeForDeviceTypeWithMacOs() {
        Service.OSType osType = CostUtils.getOSTypeForDeviceType("Mac Laptop");
        assertEquals(Service.OSType.MAC, osType);
    }

    @Test
    public void testGetOSTypeForDeviceTypeWithWindowsOs() {
        Service.OSType osType = CostUtils.getOSTypeForDeviceType("Windows server");
        assertEquals(Service.OSType.WINDOWS, osType);
    }

    @Test
    public void testGetOSTypeForDeviceTypeWithNoOs() {
        Service.OSType osType = CostUtils.getOSTypeForDeviceType("Linux server");
        assertEquals(Service.OSType.DEFAULT, osType);
    }
}
