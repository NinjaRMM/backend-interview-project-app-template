package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ITServiceRepository;
import com.ninjaone.backendinterviewproject.exceptions.DeviceNotFoundException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotAvailableForDeviceException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotFoundException;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ITService;
import com.ninjaone.backendinterviewproject.model.enums.DeviceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository repository;

    private final ReportService reportService;
    private final ITServiceRepository serviceRepository;

    public DeviceService(DeviceRepository repository, ReportService reportService, ITServiceRepository serviceRepository) {
        this.repository = repository;
        this.reportService = reportService;
        this.serviceRepository = serviceRepository;
    }

    public Device saveDeviceEntity(Device device){
        return repository.save(device);
    }

    public void deleteDeviceEntityById(Long id) {
        repository.deleteById(id);
    }

    public Device getDeviceById(Long id) throws DeviceNotFoundException {
        return repository.findById(id).orElseThrow(DeviceNotFoundException::new);
    }

    /**
     * Function that links the services to the devices, only one of the same service can be provided to a certain device
     * The Device Type and the Service Devices allowed must match
     *
     * @param deviceId Id of the Device that will use the service
     * @param serviceId Id of the service that will be provided
     * @return Device with the service Added
     * @throws ServiceNotAvailableForDeviceException If there's a mismatch between the device type and the devices that the service can provide to
     * @throws DeviceNotFoundException If there's no device  registered to that ID
     * @throws ServiceNotFoundException If there's no Service  registered to that ID
     */
    public Device addServiceToDevice(Long deviceId, Long serviceId) throws ServiceNotAvailableForDeviceException, DeviceNotFoundException, ServiceNotFoundException {

        Device device = repository.findById(deviceId).orElseThrow(DeviceNotFoundException::new);
        ITService service = serviceRepository.findById(serviceId).orElseThrow(ServiceNotFoundException::new);

        if(service.getDeviceAttributes().contains(device.getType()) || service.getDeviceAttributes().contains(DeviceType.ANY) || device.getType() == DeviceType.ANY) {
            device.getLinkedServices().add(service);
            service.getDevicesServiced().add(device);
            serviceRepository.save(service);
            Device toReturn = repository.save(device);
            reportService.resetCache();
            return toReturn;
        }
        throw new ServiceNotAvailableForDeviceException();
    }


}
