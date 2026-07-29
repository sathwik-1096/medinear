package com.medinear.medinear.controller;

import com.medinear.medinear.dto.BillRequestDto;
import com.medinear.medinear.entity.Bill;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import com.medinear.medinear.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Bill> generateBill(@RequestBody BillRequestDto request) {
        Bill bill = billService.generateBill(request);
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @GetMapping("/{id}")
    public Optional<Bill> getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @GetMapping("/number/{billNumber}")
    public Optional<Bill> getBillByBillNumber(@PathVariable String billNumber) {
        return billService.getBillByBillNumber(billNumber);
    }

    @GetMapping("/user")
    public List<Bill> getBillsByUser(@RequestBody User user) {
        return billService.getBillsByUser(user);
    }

    @GetMapping("/pharmacy")
    public List<Bill> getBillsByPharmacy(@RequestBody Pharmacy pharmacy) {
        return billService.getBillsByPharmacy(pharmacy);
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }
}