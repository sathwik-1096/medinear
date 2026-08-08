package com.medinear.medinear.repository;

import com.medinear.medinear.entity.Bill;
import com.medinear.medinear.entity.Pharmacy;
import com.medinear.medinear.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByUser(User user);

    List<Bill> findByPharmacy(Pharmacy pharmacy);

    Optional<Bill> findByBillNumber(String billNumber);

    boolean existsByBillNumber(String billNumber);
}