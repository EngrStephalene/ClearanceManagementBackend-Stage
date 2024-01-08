package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.ClearanceDto;
import com.clearance.management.backend.dto.ClearanceDtoWithStudentName;
import com.clearance.management.backend.dto.ClearanceWithFacultyDTO;
import com.clearance.management.backend.dto.StudentClearanceHeaderDTO;
import com.clearance.management.backend.entity.Clearance;
import com.clearance.management.backend.request.ClearanceRequest;
import com.clearance.management.backend.request.RejectClearanceRequest;
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

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/")
    public ResponseEntity<List<ClearanceDtoWithStudentName>> getAllClearance() {
        System.out.println("GET ALL Clearance API is called.");
        List<ClearanceDtoWithStudentName> clearanceDtoList = clearanceService.getAllClearanceRequest();
        return new ResponseEntity<>(clearanceDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("{studentId}")
    public ResponseEntity<List<ClearanceWithFacultyDTO>> getClearanceByStudentId(@PathVariable("studentId") Integer studentId) {
        System.out.println("GET CLEARANCE BY STUDENT ID API is called.");
        List<ClearanceWithFacultyDTO> clearanceDtoList = clearanceService.getClearanceByStudentId(studentId);
        return new ResponseEntity<>(clearanceDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/student-info/{userId}")
    public ResponseEntity<StudentClearanceHeaderDTO> getStudentInfoForHeader(@PathVariable("userId") Integer userId) {
        System.out.println("GET STUDENT INFO FOR HEADER API is called.");
        StudentClearanceHeaderDTO studentClearanceHeaderDTO = clearanceService.getStudentInformationForHeader(userId);
        return new ResponseEntity<>(studentClearanceHeaderDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<ClearanceDtoWithStudentName>> getClearanceByFacultyId(@PathVariable("facultyId") Integer facultyId) {
        System.out.println("GET CLEARANCE BY STUDENT ID API is called.");
        List<ClearanceDtoWithStudentName> clearanceDtoList = clearanceService.getClearanceByFacultyId(facultyId);
        return new ResponseEntity<>(clearanceDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @DeleteMapping("{studentId}")
    public ResponseEntity<String> deleteClearanceByStudentId(@PathVariable("studentId") Integer studentId) {
        System.out.println("Delete clearance by student id api is called");
        clearanceService.deleteClearanceByStudentId(studentId);
        return new ResponseEntity<>("Successfully deleted clearances.", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PatchMapping("{id}")
    public ResponseEntity<ClearanceDto> markClearanceAsApprove(@PathVariable("id") Integer id) {
        System.out.println("MARK CLEARANCE AS APPROVE API is called.");
        ClearanceDto updatedClearance = clearanceService.markAsApprove(id);
        return new ResponseEntity<>(updatedClearance, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PatchMapping("/reject")
    public ResponseEntity<ClearanceDto> markClearanceAsReject(@RequestBody RejectClearanceRequest request) {
        System.out.println("MARK CLEARANCE AS REJECT API is called.");
        ClearanceDto updatedClearance = clearanceService.markAsReject(request);
        return new ResponseEntity<>(updatedClearance, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PostMapping("/add")
    public ResponseEntity<List<ClearanceDto>> addClearance(@RequestBody ClearanceRequest request) {
        List<ClearanceDto> savedClearance = clearanceService.addClearance(request);
        return new ResponseEntity<>(savedClearance, HttpStatus.CREATED);
    }
}
