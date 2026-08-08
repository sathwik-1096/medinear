package com.medinear.medinear.service.Impl;

import com.medinear.medinear.entity.Medicine;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.PharmacyInventory;
import com.medinear.medinear.repository.MedicineRepository;
import com.medinear.medinear.repository.PharmacyInventoryRepository;
import com.medinear.medinear.repository.PharmacyRepository;
import com.medinear.medinear.service.PharmacyInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyInventoryServiceImpl implements PharmacyInventoryService {

    private final PharmacyInventoryRepository pharmacyInventoryRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    public PharmacyInventoryServiceImpl(
            PharmacyInventoryRepository pharmacyInventoryRepository,
            PharmacyRepository pharmacyRepository,
            MedicineRepository medicineRepository) {

        this.pharmacyInventoryRepository = pharmacyInventoryRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
    }

    // Implement methods here

    @Override
    public PharmacyInventory addInventory(PharmacyInventory inventory) {
        return pharmacyInventoryRepository.save(inventory);
    }

    @Override
    public PharmacyInventory updateInventory(
            Long id,
            PharmacyInventory inventory) {

        PharmacyInventory existingInventory =
                pharmacyInventoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        existingInventory.setAvailableQuantity(
                inventory.getAvailableQuantity());

        existingInventory.setUnit(
                inventory.getUnit());

        existingInventory.setPrice(
                inventory.getPrice());

        existingInventory.setBatchNumber(
                inventory.getBatchNumber());

        existingInventory.setExpiryDate(
                inventory.getExpiryDate());

        existingInventory.setMinimumStock(
                inventory.getMinimumStock());

        return pharmacyInventoryRepository.save(
                existingInventory);
    }

    @Override
    public void deleteInventory(Long id) {

        PharmacyInventory inventory =
                pharmacyInventoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        pharmacyInventoryRepository.delete(inventory);
    }

    @Override
    public Optional<PharmacyInventory> getInventoryById(Long id) {
        return pharmacyInventoryRepository.findById(id);
    }

    @Override
    public List<PharmacyInventory> getInventoryByPharmacy(Long pharmacyId) {

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() ->
                        new RuntimeException("Pharmacy not found"));

        return pharmacyInventoryRepository.findByPharmacy(pharmacy);
    }

    @Override
    public List<PharmacyInventory> getInventoryByMedicine(Long medicineId) {

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found"));

        return pharmacyInventoryRepository.findByMedicine(medicine);
    }

    @Override
    public Optional<PharmacyInventory> getInventoryByPharmacyAndMedicine(
            Long pharmacyId,
            Long medicineId) {

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() ->
                        new RuntimeException("Pharmacy not found"));

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found"));

        return pharmacyInventoryRepository.findByPharmacyAndMedicine(
                pharmacy,
                medicine);
    }

    @Override
    public List<PharmacyInventory> searchInventoryByMedicineName(
            String medicineName) {

        return pharmacyInventoryRepository
                .findByMedicine_MedicineNameContainingIgnoreCase(
                        medicineName);
    }
}