package com.medinear.medinear.service;

import com.medinear.medinear.entity.Bill;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;

import java.util.List;
import java.util.Optional;

public interface BillService {

    Bill createBill(Bill bill);

    Optional<Bill> getBillById(Long id);

    Optional<Bill> getBillByBillNumber(String billNumber);

    List<Bill> getBillsByUser(User user);

    List<Bill> getBillsByPharmacy(Pharmacy pharmacy);

    List<Bill> getAllBills();
}