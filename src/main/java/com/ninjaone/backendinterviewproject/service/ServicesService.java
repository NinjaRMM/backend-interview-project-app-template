package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.dto.ServiceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;

public interface ServicesService {
    String createService(ServiceDto deviceDto) throws CustomValidationException;

    void deleteServiceById(String id);
}
