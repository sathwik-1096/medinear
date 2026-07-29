package com.medinear.medinear.service.Impl;

import com.medinear.medinear.dto.BillItemRequestDto;
import com.medinear.medinear.dto.BillRequestDto;
import com.medinear.medinear.entity.*;
import com.medinear.medinear.repository.*;
import com.medinear.medinear.service.BillService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final PharmacyInventoryRepository pharmacyInventoryRepository;

    public BillServiceImpl(BillRepository billRepository,
                           UserRepository userRepository,
                           PharmacyRepository pharmacyRepository,
                           MedicineRepository medicineRepository,
                           PharmacyInventoryRepository pharmacyInventoryRepository) {

        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacyInventoryRepository = pharmacyInventoryRepository;
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

    @Override
    @Transactional
    public Bill generateBill(BillRequestDto request) {

        User customer = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Pharmacy pharmacy = pharmacyRepository.findById(request.getPharmacyId())
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

        Bill bill = new Bill();

        bill.setUser(customer);
        bill.setPharmacy(pharmacy);
        bill.setPaymentMethod(request.getPaymentMethod());

        // ↓↓↓ Write it here ↓↓↓

        double totalAmount = 0.0;

        for (BillItemRequestDto itemRequest : request.getItems()) {

            // Find medicine
            Medicine medicine = medicineRepository.findById(itemRequest.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found"));

            // Find medicine in pharmacy inventory
            PharmacyInventory inventory = pharmacyInventoryRepository
                    .findByPharmacyAndMedicine(pharmacy, medicine)
                    .orElseThrow(() -> new RuntimeException("Medicine not available in this pharmacy"));

            // Check stock
            if (inventory.getAvailableQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException(
                        medicine.getMedicineName() + " is out of stock or insufficient quantity."
                );
            }

            // Create BillItem
            BillItem billItem = new BillItem();
            billItem.setBill(bill);
            billItem.setMedicine(medicine);
            billItem.setQuantity(itemRequest.getQuantity());
            billItem.setUnit(inventory.getUnit());
            billItem.setUnitPrice(inventory.getPrice());

            // subtotal will be calculated automatically by @PrePersist
            bill.getBillItems().add(billItem);

            // Add to total
            totalAmount += inventory.getPrice() * itemRequest.getQuantity();

            // Reduce inventory
            inventory.setAvailableQuantity(
                    inventory.getAvailableQuantity() - itemRequest.getQuantity()
            );

            pharmacyInventoryRepository.save(inventory);
        }

        // More code will come here...
        bill.setTotalAmount(totalAmount);

        double discount = request.getDiscount() != null ? request.getDiscount() : 0.0;
        double tax = request.getTax() != null ? request.getTax() : 0.0;

        bill.setDiscount(discount);
        bill.setTax(tax);

        double finalAmount = totalAmount - discount + tax;
        bill.setFinalAmount(finalAmount);

// Generate bill number (simple example)
        bill.setBillNumber("MED-" + System.currentTimeMillis());

        return billRepository.save(bill);
    }
}