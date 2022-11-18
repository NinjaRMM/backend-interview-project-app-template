package com.ninjaone.backendinterviewproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.DeviceNinjaOne;
import com.ninjaone.backendinterviewproject.service.api.DeviceNinjaOneServiceInterface;


@RestController
@RequestMapping(path = "api/v1/admin/device")
public class DeviceNinjaOneController
        extends AbstractController<DeviceNinjaOne, Long, DeviceNinjaOneServiceInterface> {

}
