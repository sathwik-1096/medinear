package com.medinear.medinear.service;

import com.medinear.medinear.entity.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    Medicine addMedicine(Medicine medicine);

    Medicine updateMedicine(Long id, Medicine medicine);

    void deleteMedicine(Long id);

    Optional<Medicine> getMedicineById(Long id);

    Optional<Medicine> getMedicineByName(String medicineName);

    List<Medicine> getAllMedicines();

    List<Medicine> searchMedicines(String medicineName);
}
