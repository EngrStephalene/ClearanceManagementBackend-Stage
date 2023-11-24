package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViolationRepository extends JpaRepository<Violation,Integer> {
    Optional<List<Violation>> findViolationByStudentId(String studentId);
}
