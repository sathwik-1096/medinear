package com.medinear.medinear.dto;

import com.medinear.medinear.enums.PaymentMethod;

import java.util.List;

public class BillRequestDto {

    private Long customerId;

    private Long pharmacyId;

    private PaymentMethod paymentMethod;

    private Double discount;

    private Double tax;

    private List<BillItemRequestDto> items;

    public BillRequestDto() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public List<BillItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<BillItemRequestDto> items) {
        this.items = items;
    }
}
