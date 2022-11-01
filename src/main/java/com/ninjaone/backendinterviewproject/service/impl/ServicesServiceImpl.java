package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.dto.ServiceDto;
import com.ninjaone.backendinterviewproject.exceptions.CustomValidationException;
import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import com.ninjaone.backendinterviewproject.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    public static final String SERVICE_ALREADY_EXISTS = "Service Already exists";
    public static final String SERVICE_CREATED = "Service created";
    private final ServiceRepository serviceRepository;
    private final ModelMapper objectMapper;

    @Override
    public String createService(ServiceDto serviceDto) throws CustomValidationException {

        var value = serviceRepository.countRecordByIdAndName(serviceDto.getId(), serviceDto.getName());
        if(value > 0){
            throw new CustomValidationException(SERVICE_ALREADY_EXISTS);
        }else {
            com.ninjaone.backendinterviewproject.model.Service service = convertToEntity(serviceDto);
            serviceRepository.save(service);
            return SERVICE_CREATED;
        }
    }


    @Override
    public void deleteServiceById(String id) {
        var value = serviceRepository.findById(Integer.parseInt(id));
        if(value.isPresent()){
            serviceRepository.delete(value.get());
        }else {
            throw new EntityNotFoundException("Device not Found");
        }
    }

    private com.ninjaone.backendinterviewproject.model.Service convertToEntity(ServiceDto serviceDto){
        return objectMapper.map(serviceDto, com.ninjaone.backendinterviewproject.model.Service.class);
    }


    private ServiceDto convertToDto(com.ninjaone.backendinterviewproject.model.Service service) {
        return objectMapper.map(service, ServiceDto.class);
    }

    //    @Override
//    @Transactional
//    public String updateDevice(DeviceDto deviceDto) {
//        var value = serviceRepository.findById(deviceDto.getId());
//        if(value.isPresent()){
//            var responseEntity = value.get();
//            responseEntity.setSystemName(deviceDto.getSystemName());
//            responseEntity.setType(deviceDto.getType());
//            serviceRepository.save(responseEntity);
//        }else{
//            throw new EntityNotFoundException("Device not Found");
//        }
//        return "Device updated";
//    }

//    @Override
//    public DeviceDto findDeviceById(String id) {
//
//        var value = serviceRepository.findById(Integer.parseInt(id));
//        if(value.isPresent()){
//            return convertToDto(value.get());
//        }else {
//            throw new EntityNotFoundException("Device not Found");
//        }
//    }

}
