package com.medinear.medinear.service.Impl;

import com.medinear.medinear.entity.Medicine;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.PharmacyInventory;
import com.medinear.medinear.repository.PharmacyInventoryRepository;
import com.medinear.medinear.service.PharmacyInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyInventoryServiceImpl implements PharmacyInventoryService {

    private final PharmacyInventoryRepository pharmacyInventoryRepository;

    public PharmacyInventoryServiceImpl(PharmacyInventoryRepository pharmacyInventoryRepository) {
        this.pharmacyInventoryRepository = pharmacyInventoryRepository;
    }

    // Implement methods here

    @Override
    public PharmacyInventory addInventory(PharmacyInventory inventory) {
        return pharmacyInventoryRepository.save(inventory);
    }

    @Override
    public PharmacyInventory updateInventory(Long id, PharmacyInventory inventory) {

        PharmacyInventory existingInventory = pharmacyInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        existingInventory.setPharmacy(inventory.getPharmacy());
        existingInventory.setMedicine(inventory.getMedicine());
        existingInventory.setAvailableQuantity(inventory.getAvailableQuantity());
        existingInventory.setUnit(inventory.getUnit());
        existingInventory.setPrice(inventory.getPrice());
        existingInventory.setBatchNumber(inventory.getBatchNumber());
        existingInventory.setExpiryDate(inventory.getExpiryDate());
        existingInventory.setMinimumStock(inventory.getMinimumStock());
        existingInventory.setLastUpdated(inventory.getLastUpdated());

        return pharmacyInventoryRepository.save(existingInventory);
    }

    @Override
    public void deleteInventory(Long id) {
        pharmacyInventoryRepository.deleteById(id);
    }

    @Override
    public Optional<PharmacyInventory> getInventoryById(Long id) {
        return pharmacyInventoryRepository.findById(id);
    }

    @Override
    public List<PharmacyInventory> getInventoryByPharmacy(Pharmacy pharmacy) {
        return pharmacyInventoryRepository.findByPharmacy(pharmacy);
    }

    @Override
    public List<PharmacyInventory> getInventoryByMedicine(Medicine medicine) {
        return pharmacyInventoryRepository.findByMedicine(medicine);
    }

    @Override
    public Optional<PharmacyInventory> getInventoryByPharmacyAndMedicine(
            Pharmacy pharmacy,
            Medicine medicine) {

        return pharmacyInventoryRepository.findByPharmacyAndMedicine(pharmacy, medicine);
    }
}