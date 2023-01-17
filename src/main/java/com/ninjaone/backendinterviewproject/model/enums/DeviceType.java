package com.ninjaone.backendinterviewproject.model.enums;


public enum DeviceType {
    WINDOWS_WORKSTATION("WW"), WINDOWS_SERVER("WS"), MAC("M"), ANY("A");

    private String code;

    private DeviceType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
