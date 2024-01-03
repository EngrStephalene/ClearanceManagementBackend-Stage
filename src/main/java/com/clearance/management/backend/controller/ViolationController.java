package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.ViolationDto;
import com.clearance.management.backend.dto.ViolationRequest;
import com.clearance.management.backend.dto.ViolationWithStudentNameDto;
import com.clearance.management.backend.entity.Violation;
import com.clearance.management.backend.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/violation")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
public class ViolationController {

    @Autowired
    ViolationService violationService;

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @PostMapping("/add/{studentNumber}")
    public ResponseEntity<ViolationDto> addViolation(@RequestBody ViolationRequest request,
                                                     @PathVariable(name = "studentNumber") String studentNumber) {
        System.out.println("ADD VIOLATION API IS CALLED.");
        ViolationDto savedViolation = violationService.addViolation(request, studentNumber);
        return new ResponseEntity<>(savedViolation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViolationDto> getViolation(@PathVariable("id") Integer id) {
        System.out.println("GET VIOLATION API IS CALLED.");
        ViolationDto violationDto = violationService.getViolation(id);
        return new ResponseEntity<>(violationDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY', 'STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ViolationDto>> getViolationByStudentId(@PathVariable("studentId") String studentId) {
        System.out.println("GET VIOLATION BY STUDENT ID API IS CALLED.");
        List<ViolationDto> violationDtoList = violationService.getStudentViolationByStudentId(studentId);
        System.out.println(violationDtoList);
        return new ResponseEntity<>(violationDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY', 'STUDENT')")
    @GetMapping("/")
    public ResponseEntity<List<ViolationWithStudentNameDto>> getAllViolation() {
        System.out.println("GET ALL VIOLATION API IS CALLED.");
        List<ViolationWithStudentNameDto> violationDtoList =  violationService.getAllViolation();
        return new ResponseEntity<>(violationDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @PutMapping("{id}")
    public ResponseEntity<ViolationDto> updateViolation(@RequestBody ViolationRequest request,@PathVariable("id") Integer id) {
        System.out.println("UPDATE VIOLATION API IS CALLED.");
        ViolationDto updatedViolationDto = violationService.updateViolation(request, id);
        return new ResponseEntity<>(updatedViolationDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @PatchMapping("/complete/{id}")
    public ResponseEntity<ViolationDto> completeViolation(@PathVariable("id") Integer id) {
        System.out.println("PATCH VIOLATION API IS CALLED.");
        ViolationDto completedViolation = violationService.completeViolation(id);
        return new ResponseEntity<>(completedViolation, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteViolation(@PathVariable("id") Integer id) {
        System.out.println("DELETE VIOLATION API IS CALLED.");
        violationService.deleteToDo(id);
        return new ResponseEntity<>("Violation deleted successfully.", HttpStatus.OK);
    }
}
