package com.ninjaone.backendinterviewproject.exceptions;

public class ServiceNotAvailableForDeviceException extends Exception{
    public ServiceNotAvailableForDeviceException() {
    }

    public ServiceNotAvailableForDeviceException(String message) {
        super(message);
    }
}
