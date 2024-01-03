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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody UserRequest request) {
        System.out.println("Verify password API is called.");
        Boolean check = userService.verifyPassword(request);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @PatchMapping("/update-password")
    public ResponseEntity<AppUserDto> updatePassword(@RequestBody UpdatePasswordRequest request) {
        System.out.println("Update password API is called.");
        AppUserDto updatedUser = userService.updatePassword(request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserProfileDto> updateUserInformation(@RequestBody UpdateUserProfileRequest request) {
        System.out.println("Update user information API is called.");
        UserProfileDto updatedUserProfile = userService.updateUserInformation(request);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<String> getRoleByUserId(@PathVariable("id") Integer id) {
        System.out.println("Get role by user id API is called.");
        String role = userService.getRoleByUserId(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
