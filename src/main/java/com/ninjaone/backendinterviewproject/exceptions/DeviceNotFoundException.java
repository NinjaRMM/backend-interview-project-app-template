package com.ninjaone.backendinterviewproject.exceptions;

public class DeviceNotFoundException extends Exception{
    public DeviceNotFoundException() {
        super();
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
