package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.dto.UserProfileDto;
import com.clearance.management.backend.request.UpdatePasswordRequest;
import com.clearance.management.backend.request.UpdateUserProfileRequest;
import com.clearance.management.backend.request.UserRequest;
import com.clearance.management.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PostMapping("/")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody UserRequest request) {
        System.out.println("Verify password API is called.");
        Boolean check = userService.verifyPassword(request);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PatchMapping("/update-password")
    public ResponseEntity<AppUserDto> updatePassword(@RequestBody UpdatePasswordRequest request) {
        System.out.println("Update password API is called.");
        AppUserDto updatedUser = userService.updatePassword(request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PutMapping("/update")
    public ResponseEntity<UserProfileDto> updateUserInformation(@RequestBody UpdateUserProfileRequest request) {
        System.out.println("Update user information API is called.");
        UserProfileDto updatedUserProfile = userService.updateUserInformation(request);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/role/{id}")
    public ResponseEntity<String> getRoleByUserId(@PathVariable("id") Integer id) {
        System.out.println("Get role by user id API is called.");
        String role = userService.getRoleByUserId(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
