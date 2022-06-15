package com.ninjaone.backendinterviewproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;


@RestController
@RequestMapping(path = "api/admin/v1/service")
public class ServiceNinjaOneController
        extends AbstractController<ServiceNinjaOne, Long, ServiceNinjaOneServiceInterface> {

    public ServiceNinjaOneController(ServiceNinjaOneServiceInterface service) {
        super(service);
    }
}
