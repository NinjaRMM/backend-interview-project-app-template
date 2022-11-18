package com.ninjaone.backendinterviewproject.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceNinjaOneRepository;
import com.ninjaone.backendinterviewproject.exception.GenericException;
import com.ninjaone.backendinterviewproject.exception.ServiceNinjaOneDuplicateEntityException;
import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;

@Service
public class ServiceNinjaOneService extends AbstractServiceImpl<ServiceNinjaOne, Long, ServiceNinjaOneRepository>
                implements ServiceNinjaOneServiceInterface {
        @Override
        public ServiceNinjaOne create(ServiceNinjaOne entity) throws GenericException {

                Optional<ServiceNinjaOne> serviceNinjaOne = getRepository().findByServiceNameAndOperatingSystem(
                                entity.getServiceName(), entity.getOperatingSystem());
                if (serviceNinjaOne.isPresent())
                        throw new ServiceNinjaOneDuplicateEntityException(
                                        "The service name: " + entity.getServiceName() + " and operating system:"
                                                        + entity.getOperatingSystem().getId() + " already exists");
                return super.create(entity);
        }
}