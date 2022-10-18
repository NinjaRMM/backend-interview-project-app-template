package com.ninjaone.backendinterviewproject.dto.response;

import java.util.ArrayList;

public class InvoiceDTO {
    private String customerName;
    private ArrayList<InvoiceItemDTO> invoiceItemDTOS;
    private Double InvoiceTotal;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceItemDTO> getInvoiceItemDTOS() {
        return invoiceItemDTOS;
    }

    public void setInvoiceItemDTOS(ArrayList<InvoiceItemDTO> invoiceItemDTOS) {
        this.invoiceItemDTOS = invoiceItemDTOS;
    }

    public Double getInvoiceTotal() {
        return InvoiceTotal;
    }

    public void setInvoiceTotal(Double invoiceTotal) {
        InvoiceTotal = invoiceTotal;
    }
}
