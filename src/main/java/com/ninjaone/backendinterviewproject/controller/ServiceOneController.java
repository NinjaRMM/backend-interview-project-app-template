package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.ServiceOne;
import com.ninjaone.backendinterviewproject.service.Interfaces.IServiceOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceOne")
public class ServiceOneController {
    @Autowired
    private IServiceOneService IServiceOneService;

    public ServiceOneController(IServiceOneService IServiceOneService) {
        this.IServiceOneService = IServiceOneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ServiceOne postSampleEntity(@RequestBody ServiceOne serviceOne){
        return IServiceOneService.saveServiceOneEntity(serviceOne);
    }

    @GetMapping("/{id}")
    private ServiceOne getSampleEntity(@PathVariable String id){
        return IServiceOneService.getServiceOneEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteSampleEntity(@PathVariable String id){
        IServiceOneService.deleteServiceOneEntity(id);
    }
}
