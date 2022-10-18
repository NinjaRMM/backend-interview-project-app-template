package com.ninjaone.backendinterviewproject.exception;

public class DeviceTypeNotFoundException extends RuntimeException{

    public DeviceTypeNotFoundException() {
        super();
    }

    public DeviceTypeNotFoundException(String message) {
        super(message);
    }

}
