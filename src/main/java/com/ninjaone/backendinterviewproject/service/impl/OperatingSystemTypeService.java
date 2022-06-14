package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.OperatingSystemTypeRepository;
import com.ninjaone.backendinterviewproject.model.OperatingSystemType;
import com.ninjaone.backendinterviewproject.service.api.OperatingSystemTypeServiceInterface;

@Service
public class OperatingSystemTypeService extends AbstractServiceImpl<OperatingSystemType, String, OperatingSystemTypeRepository>  implements OperatingSystemTypeServiceInterface{
    public OperatingSystemTypeService(OperatingSystemTypeRepository repository) {
        super(repository);
    }
}