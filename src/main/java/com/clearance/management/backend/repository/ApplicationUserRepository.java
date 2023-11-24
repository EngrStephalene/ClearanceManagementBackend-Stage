package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<ApplicationUser> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

}
