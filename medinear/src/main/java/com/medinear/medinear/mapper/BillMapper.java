package com.medinear.medinear.mapper;

import com.medinear.medinear.dto.BillItemResponseDto;
import com.medinear.medinear.dto.BillResponseDto;
import com.medinear.medinear.entity.Bill;
import com.medinear.medinear.entity.BillItem;

import java.util.ArrayList;
import java.util.List;

public class BillMapper {

    public static BillResponseDto toResponseDto(Bill bill) {

        BillResponseDto response = new BillResponseDto();

        response.setBillId(bill.getId());
        response.setBillNumber(bill.getBillNumber());
        response.setCustomerName(bill.getUser().getFullName());
        response.setPharmacyName(bill.getPharmacy().getPharmacyName());
        response.setBillDate(bill.getBillDate());
        response.setPaymentMethod(bill.getPaymentMethod());
        response.setTotalAmount(bill.getTotalAmount());
        response.setDiscount(bill.getDiscount());
        response.setTax(bill.getTax());
        response.setFinalAmount(bill.getFinalAmount());

        List<BillItemResponseDto> items = new ArrayList<>();

        for (BillItem item : bill.getBillItems()) {

            BillItemResponseDto dto = new BillItemResponseDto();

            dto.setMedicineName(item.getMedicine().getMedicineName());
            dto.setQuantity(item.getQuantity());
            dto.setUnit(item.getUnit());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setSubtotal(item.getSubtotal());

            items.add(dto);
        }

        response.setItems(items);

        return response;
    }
}