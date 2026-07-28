package com.medinear.medinear.controller;

import com.medinear.medinear.entity.Medicine;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.PharmacyInventory;
import com.medinear.medinear.service.PharmacyInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class PharmacyInventoryController {

    private final PharmacyInventoryService inventoryService;

    @Autowired
    public PharmacyInventoryController(PharmacyInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public PharmacyInventory addInventory(@RequestBody PharmacyInventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @GetMapping("/{id}")
    public Optional<PharmacyInventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/pharmacy")
    public List<PharmacyInventory> getInventoryByPharmacy(@RequestBody Pharmacy pharmacy) {
        return inventoryService.getInventoryByPharmacy(pharmacy);
    }

    @GetMapping("/medicine")
    public List<PharmacyInventory> getInventoryByMedicine(@RequestBody Medicine medicine) {
        return inventoryService.getInventoryByMedicine(medicine);
    }

    @GetMapping("/pharmacy-medicine")
    public Optional<PharmacyInventory> getInventoryByPharmacyAndMedicine(
            @RequestBody Pharmacy pharmacy,
            @RequestBody Medicine medicine) {

        return inventoryService.getInventoryByPharmacyAndMedicine(pharmacy, medicine);
    }

    @PutMapping("/{id}")
    public PharmacyInventory updateInventory(@PathVariable Long id,
                                             @RequestBody PharmacyInventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}