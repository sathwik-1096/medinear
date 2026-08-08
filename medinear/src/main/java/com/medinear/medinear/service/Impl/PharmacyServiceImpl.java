package com.medinear.medinear.service.Impl;

import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import com.medinear.medinear.repository.PharmacyRepository;
import com.medinear.medinear.service.PharmacyService;
import org.springframework.stereotype.Service;
import com.medinear.medinear.enums.Role;
import com.medinear.medinear.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    private final UserRepository userRepository;

    public PharmacyServiceImpl(
            PharmacyRepository pharmacyRepository,
            UserRepository userRepository) {

        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Pharmacy addPharmacy(Pharmacy pharmacy) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        System.out.println("AUTHENTICATED USER = " + email);

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        System.out.println("OWNER ID = " + owner.getId());
        System.out.println("OWNER ROLE = " + owner.getRole());

        if (owner.getRole() != Role.OWNER) {
            throw new RuntimeException(
                    "Only pharmacy owners can add a pharmacy"
            );
        }

        pharmacy.setOwner(owner);

        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy updatePharmacy(Long id, Pharmacy pharmacy) {

        Pharmacy existingPharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pharmacy not found"));

        existingPharmacy.setPharmacyName(pharmacy.getPharmacyName());
        existingPharmacy.setLicenseNumber(pharmacy.getLicenseNumber());
        existingPharmacy.setPhoneNumber(pharmacy.getPhoneNumber());
        existingPharmacy.setEmail(pharmacy.getEmail());
        existingPharmacy.setAddress(pharmacy.getAddress());
        existingPharmacy.setCity(pharmacy.getCity());
        existingPharmacy.setState(pharmacy.getState());
        existingPharmacy.setPincode(pharmacy.getPincode());
        existingPharmacy.setLatitude(pharmacy.getLatitude());
        existingPharmacy.setLongitude(pharmacy.getLongitude());
        existingPharmacy.setOpeningTime(pharmacy.getOpeningTime());
        existingPharmacy.setClosingTime(pharmacy.getClosingTime());

        return pharmacyRepository.save(existingPharmacy);
    }

    @Override
    public void deletePharmacy(Long id) {

        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pharmacy not found"));

        pharmacyRepository.delete(pharmacy);
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
        return pharmacyRepository
                .findByPharmacyNameContainingIgnoreCase(pharmacyName);
    }
}