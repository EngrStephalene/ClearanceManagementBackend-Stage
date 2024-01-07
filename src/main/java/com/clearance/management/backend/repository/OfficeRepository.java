package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Offices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Offices,Integer> {
}
