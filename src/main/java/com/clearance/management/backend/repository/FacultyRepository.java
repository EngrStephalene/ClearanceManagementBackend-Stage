package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    @Query("select u from Faculty u where u.facultyNumber = ?1")
    Optional<Faculty> findFacultyByFacultyNumber(String facultyNumber);

    @Query("select u from Faculty u where u.applicationUser = ?1")
    Optional<Faculty> findFacultyByUserId(ApplicationUser applicationUser);
}
