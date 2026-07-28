package com.medinear.medinear.service;

import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {

    Pharmacy addPharmacy(Pharmacy pharmacy);

    Pharmacy updatePharmacy(Long id, Pharmacy pharmacy);

    void deletePharmacy(Long id);

    Optional<Pharmacy> getPharmacyById(Long id);

    List<Pharmacy> getAllPharmacies();

    List<Pharmacy> getPharmaciesByOwner(User owner);

    List<Pharmacy> searchPharmacyByName(String pharmacyName);
}