package com.medinear.medinear.repository;

import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    List<Pharmacy> findByOwner(User owner);

    List<Pharmacy> findByPharmacyNameContainingIgnoreCase(String pharmacyName);
}