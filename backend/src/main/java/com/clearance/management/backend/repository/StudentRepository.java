package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select u from Student u where u.studentNumber = ?1")
    Optional<Student> findStudentByStudentNumber(String studentNumber);
}
