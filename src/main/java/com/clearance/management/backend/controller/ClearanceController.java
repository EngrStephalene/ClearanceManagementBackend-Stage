package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.ClearanceDto;
import com.clearance.management.backend.entity.Clearance;
import com.clearance.management.backend.request.ClearanceRequest;
import com.clearance.management.backend.service.ClearanceService;
import org.hibernate.internal.util.securitymanager.SystemSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearance")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
public class ClearanceController {

    @Autowired
    ClearanceService clearanceService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY_HEAD', 'FACULTY'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
    @GetMapping("/")
    public ResponseEntity<List<ClearanceDto>> getAllClearance() {
        System.out.println("GET ALL Clearance API is called.");
        List<ClearanceDto> clearanceDtoList = clearanceService.getAllClearanceRequest();
        return new ResponseEntity<>(clearanceDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('STUDENT', 'FACULTY')")
    @GetMapping("{studentId}")
    public ResponseEntity<List<ClearanceDto>> getClearanceByStudentId(@PathVariable("studentId") Integer studentId) {
        System.out.println("GET CLEARANCE BY STUDENT ID API is called.");
        List<ClearanceDto> clearanceDtoList = clearanceService.getClearanceByStudentId(studentId);
        return new ResponseEntity<>(clearanceDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY_HEAD', 'FACULTY'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<ClearanceDto>> getClearanceByFacultyId(@PathVariable("facultyId") Integer facultyId) {
        System.out.println("GET CLEARANCE BY STUDENT ID API is called.");
        List<ClearanceDto> clearanceDtoList = clearanceService.getClearanceByFacultyId(facultyId);
        return new ResponseEntity<>(clearanceDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('STUDENT', 'FACULTY')")
    @DeleteMapping("{studentId}")
    public ResponseEntity<String> deleteClearanceByStudentId(@PathVariable("studentId") Integer studentId) {
        System.out.println("Delete clearance by student id api is called");
        clearanceService.deleteClearanceByStudentId(studentId);
        return new ResponseEntity<>("Successfully deleted clearances.", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY_HEAD', 'FACULTY'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
    @PatchMapping("{id}")
    public ResponseEntity<ClearanceDto> markClearanceAsApprove(@PathVariable("id") Integer id) {
        System.out.println("MARK CLEARANCE AS APPROVE API is called.");
        ClearanceDto updatedClearance = clearanceService.markAsApprove(id);
        return new ResponseEntity<>(updatedClearance, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY_HEAD', 'FACULTY'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
    @PatchMapping("/reject")
    public ResponseEntity<ClearanceDto> markClearanceAsReject(@PathVariable("id") Integer id, @RequestBody String remarks) {
        System.out.println("MARK CLEARANCE AS REJECT API is called.");
        ClearanceDto updatedClearance = clearanceService.markAsReject(id, remarks);
        return new ResponseEntity<>(updatedClearance, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('STUDENT', 'FACULTY')")
    @PostMapping("/add")
    public ResponseEntity<List<ClearanceDto>> addClearance(@RequestBody ClearanceRequest request) {
        List<ClearanceDto> savedClearance = clearanceService.addClearance(request);
        return new ResponseEntity<>(savedClearance, HttpStatus.CREATED);
    }
}
