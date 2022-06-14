package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.OperatingSystemRepository;
import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.service.api.OperatingSystemServiceInterface;

@Service
public class OperatingSystemService extends AbstractServiceImpl<OperatingSystem, String, OperatingSystemRepository>  implements OperatingSystemServiceInterface{
    public OperatingSystemService(OperatingSystemRepository repository) {
        super(repository);
    }
}