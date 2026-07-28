package com.medinear.medinear.service.Impl;

import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import com.medinear.medinear.repository.PharmacyRepository;
import com.medinear.medinear.service.PharmacyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    // Implement methods here

    @Override
    public Pharmacy addPharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy updatePharmacy(Long id, Pharmacy pharmacy) {

        Pharmacy existingPharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

        existingPharmacy.setPharmacyName(pharmacy.getPharmacyName());
        existingPharmacy.setAddress(pharmacy.getAddress());
        existingPharmacy.setLatitude(pharmacy.getLatitude());
        existingPharmacy.setLongitude(pharmacy.getLongitude());
        existingPharmacy.setOpeningTime(pharmacy.getOpeningTime());
        existingPharmacy.setClosingTime(pharmacy.getClosingTime());
        existingPharmacy.setOwner(pharmacy.getOwner());

        return pharmacyRepository.save(existingPharmacy);
    }

    @Override
    public void deletePharmacy(Long id) {
        pharmacyRepository.deleteById(id);
    }

    @Override
    public Optional<Pharmacy> getPharmacyById(Long id) {
        return pharmacyRepository.findById(id);
    }

    @Override
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    @Override
    public List<Pharmacy> getPharmaciesByOwner(User owner) {
        return pharmacyRepository.findByOwner(owner);
    }

    @Override
    public List<Pharmacy> searchPharmacyByName(String pharmacyName) {
        return pharmacyRepository.findByPharmacyNameContainingIgnoreCase(pharmacyName);
    }
}