package com.clearance.management.backend.service;


import com.clearance.management.backend.dto.*;
import com.clearance.management.backend.request.AppUserRoleRequest;

public interface AuthenticationService {
    AppUserDto registerStudent(String firstName, String lastName, String email);

    AppUserDto registerFaculty(String firstName, String lastName, String email);

    AppUserDto registerFacultyHead(String firstName, String lastName, String email, String role);

    JwtAuthResponseDto login(LoginDto request);

    void setPassword(String password);

    String getPassword();
}
