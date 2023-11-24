package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Clearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClearanceRepository extends JpaRepository<Clearance,Integer> {
    Optional<List<Clearance>> findClearanceByStudentId(String studentId);

    Optional<List<Clearance>> findClearanceByFacultyId(String facultyId);
}
