package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.ITService;
import com.ninjaone.backendinterviewproject.service.ITServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class ITServiceController {

    private final ITServiceService itService;

    public ITServiceController(ITServiceService itService) {
        this.itService = itService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ITService postServiceEntity(@RequestBody ITService iTService){
        return itService.saveService(iTService);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteServiceEntityById(@PathVariable Long id) {
        itService.deleteService(id);
    }
}
