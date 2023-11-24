package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findByDepartmentName(String departmentName);
}
