package com.medinear.medinear.repository;

import com.medinear.medinear.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByMedicineNameIgnoreCase(String medicineName);

    List<Medicine> findByMedicineNameContainingIgnoreCase(String medicineName);

    List<Medicine> findByCategoryIgnoreCase(String category);

    List<Medicine> findByManufacturerIgnoreCase(String manufacturer);
}