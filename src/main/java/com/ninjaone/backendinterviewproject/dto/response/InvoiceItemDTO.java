package com.ninjaone.backendinterviewproject.dto.response;

public class InvoiceItemDTO {
    private Integer numberOfItems;
    private String itemDeviceName;
    private String itemServiceName;
    private Double itemUnitCost;

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getItemDeviceName() {
        return itemDeviceName;
    }

    public void setItemDeviceName(String itemDeviceName) {
        this.itemDeviceName = itemDeviceName;
    }

    public Double getItemUnitCost() {
        return itemUnitCost;
    }

    public void setItemUnitCost(Double itemUnitCost) {
        this.itemUnitCost = itemUnitCost;
    }

    public String getItemServiceName() {
        return itemServiceName;
    }

    public void setItemServiceName(String itemServiceName) {
        this.itemServiceName = itemServiceName;
    }
}
