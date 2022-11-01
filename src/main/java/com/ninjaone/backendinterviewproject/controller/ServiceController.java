package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.ServiceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.service.ServicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
@Slf4j
public class ServiceController {

    private final ServicesService servicesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private  String createService(@RequestBody ServiceDto serviceDto) throws CustomValidationException {
        log.info("createService-request: " + serviceDto);
        var serviceResponse = servicesService.createService(serviceDto);
        log.info("createService-response: " + serviceResponse);
        return serviceResponse;
    }

/*    @PutMapping
    public ResponseEntity<String> updateDevice(@RequestBody DeviceDto deviceDto)
    {
        log.info("updateDevice-request: " + deviceDto);
        var serviceResponse = servicesService.(deviceDto);
        log.info("updateDevice-response: " + serviceResponse);
        return ResponseEntity.ok(serviceResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> findDeviceById(@PathVariable String id) {
        var response = servicesService.findDeviceById(id);
        log.info("Response: "+response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteServiceById(@PathVariable String id){
        log.info("deleteServiceById-request: " + id);
        servicesService.deleteServiceById(id);
    }

}
