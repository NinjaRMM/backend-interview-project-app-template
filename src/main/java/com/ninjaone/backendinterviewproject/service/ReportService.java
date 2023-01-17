package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ITServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ReportService {

    /**
        Small rudimentary cache, HashMap in memory of the application
     */
    private HashMap<String, Double> cache = new HashMap<>();
    private final ITServiceRepository serviceRepository;

    public ReportService(ITServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * Retrieves the total amount for the current database state, checks for existing Cache or revalidates
     * @return The amount of the cost of the services provided
     */
    public Double getTotalAmount() {
        if(!cache.containsKey("total")) {
            resetCache();
        }

        return cache.get("total");
    }

    /**
     * Resets cache on demand, re-queries.
     */
    public void resetCache() {
        cache.remove("total");
        cache.put("total", serviceRepository.getTotalFromServices());

    }
}
