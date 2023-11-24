package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.SubjectDto;
import com.clearance.management.backend.request.SubjectRequest;
import com.clearance.management.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin("clearance-management-frontend-stage.vercel.app")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'FACULTY', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        System.out.println("GET ALL subject api is called.");
        List<SubjectDto> subjectList = subjectService.getAllSubject();
        return new ResponseEntity<>(subjectList, HttpStatus.OK);
    }

    @GetMapping("/dept")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'FACULTY', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    public ResponseEntity<List<SubjectDto>> getSubjectByDeptId(@RequestParam(name = "id") Integer deptId) {
        System.out.println("GET SUBJECTS BY DEPT. api is called.");
        List<SubjectDto> subjectDtoList = subjectService.getSubjectByDeptId(deptId);
        return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto request) {
        System.out.println("ADD subject api is called.");
        SubjectDto savedSubjectDto = subjectService.addSubject(request);
        return new ResponseEntity<>(savedSubjectDto, HttpStatus.CREATED);
    }

    @PostMapping("/add-subj")
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    public ResponseEntity<SubjectDto> addSubjectOnSelectedDept(@RequestBody SubjectRequest request) {
        System.out.print("ADD subject by department id api is called");
        SubjectDto savedSubject = subjectService.addSubjectByDept(request);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectRequest request, @PathVariable("id") Integer id) {
        System.out.println("UPDATE subject api is called.");
        SubjectDto updatedSubject = subjectService.updateSubject(request, id);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Integer id) {
        System.out.println("DELETE subject api is called");
        subjectService.deleteSubject(id);
        return new ResponseEntity<>("Subject deleted successfully.", HttpStatus.OK);
    }
}
