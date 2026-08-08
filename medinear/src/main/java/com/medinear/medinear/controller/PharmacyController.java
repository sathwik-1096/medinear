package com.medinear.medinear.controller;

import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import com.medinear.medinear.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @PostMapping
    public Pharmacy addPharmacy(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.addPharmacy(pharmacy);
    }

    @GetMapping("/{id}")
    public Optional<Pharmacy> getPharmacyById(@PathVariable Long id) {
        return pharmacyService.getPharmacyById(id);
    }

    @GetMapping
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }

    @GetMapping("/search/{name}")
    public List<Pharmacy> searchPharmacyByName(@PathVariable String name) {
        return pharmacyService.searchPharmacyByName(name);
    }

    @PutMapping("/{id}")
    public Pharmacy updatePharmacy(@PathVariable Long id,
                                   @RequestBody Pharmacy pharmacy) {
        return pharmacyService.updatePharmacy(id, pharmacy);
    }

    @DeleteMapping("/{id}")
    public void deletePharmacy(@PathVariable Long id) {
        pharmacyService.deletePharmacy(id);
    }
}