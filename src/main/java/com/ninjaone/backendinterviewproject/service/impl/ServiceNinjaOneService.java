package com.ninjaone.backendinterviewproject.service.impl;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceNinjaOneRepository;
import com.ninjaone.backendinterviewproject.model.ServiceId;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;

@Service
public class ServiceNinjaOneService extends AbstractServiceImpl<ServiceNinjaOne, ServiceId, ServiceNinjaOneRepository>  implements ServiceNinjaOneServiceInterface{
    public ServiceNinjaOneService(ServiceNinjaOneRepository repository) {
        super(repository);
    }
}