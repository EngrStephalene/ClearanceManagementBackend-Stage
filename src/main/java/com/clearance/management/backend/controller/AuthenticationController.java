package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.FacultyDto;
import com.clearance.management.backend.dto.JwtAuthResponseDto;
import com.clearance.management.backend.dto.LoginDto;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.request.AppUserRoleRequest;
import com.clearance.management.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){
        System.out.println("Login API is called.");
        JwtAuthResponseDto jwtAuthResponseDto = authenticationService.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.OK);
    }
}
