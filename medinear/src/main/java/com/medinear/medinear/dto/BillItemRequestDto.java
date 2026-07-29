package com.medinear.medinear.dto;

public class BillItemRequestDto {

    private Long medicineId;

    private Integer quantity;

    public BillItemRequestDto() {
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}