package com.medinear.medinear.service.Impl;

import com.medinear.medinear.entity.Bill;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import com.medinear.medinear.repository.BillRepository;
import com.medinear.medinear.service.BillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    // Implement methods here

    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Optional<Bill> getBillByBillNumber(String billNumber) {
        return billRepository.findByBillNumber(billNumber);
    }

    @Override
    public List<Bill> getBillsByUser(User user) {
        return billRepository.findByUser(user);
    }

    @Override
    public List<Bill> getBillsByPharmacy(Pharmacy pharmacy) {
        return billRepository.findByPharmacy(pharmacy);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}