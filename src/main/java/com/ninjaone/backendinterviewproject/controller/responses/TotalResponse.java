package com.ninjaone.backendinterviewproject.controller.responses;

public class TotalResponse {
    private double totalAmount;

    public TotalResponse(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
