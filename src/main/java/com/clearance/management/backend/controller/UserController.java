package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.request.UpdatePasswordRequest;
import com.clearance.management.backend.request.UserRequest;
import com.clearance.management.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
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
}
