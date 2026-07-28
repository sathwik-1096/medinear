package com.medinear.medinear.service.Impl;

import com.medinear.medinear.entity.Medicine;
import com.medinear.medinear.repository.MedicineRepository;
import com.medinear.medinear.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // Implement methods here

    @Override
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicine) {

        Medicine existingMedicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        existingMedicine.setMedicineName(medicine.getMedicineName());
        existingMedicine.setManufacturer(medicine.getManufacturer());
        existingMedicine.setStrength(medicine.getStrength());
        existingMedicine.setCategory(medicine.getCategory());

        return medicineRepository.save(existingMedicine);
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    @Override
    public Optional<Medicine> getMedicineByName(String medicineName) {
        return medicineRepository.findByMedicineNameIgnoreCase(medicineName);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> searchMedicines(String medicineName) {
        return medicineRepository.findByMedicineNameContainingIgnoreCase(medicineName);
    }
}