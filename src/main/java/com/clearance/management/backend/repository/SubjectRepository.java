package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Department;
import com.clearance.management.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    List<Subject> findByDepartment(Department department);
}
