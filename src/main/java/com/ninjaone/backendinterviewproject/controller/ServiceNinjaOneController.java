package com.ninjaone.backendinterviewproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.ServiceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.ServiceNinjaOneServiceInterface;


@RestController
@RequestMapping(path = "api/v1/admin/service")
public class ServiceNinjaOneController
        extends AbstractController<ServiceNinjaOne, Long, ServiceNinjaOneServiceInterface> {
}
