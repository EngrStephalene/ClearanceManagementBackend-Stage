package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.DepartmentDto;
import com.clearance.management.backend.request.UpdateDepartmentRequest;
import com.clearance.management.backend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PostMapping("/add")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto request) {
        System.out.println("ADD department API is called");
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(request);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Integer id) {
        System.out.println("GET department API is called");
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return  new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        System.out.println("GET ALL department API is called");
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartment();
        return new ResponseEntity<>(departmentDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PutMapping("/update")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody UpdateDepartmentRequest request) {
        System.out.println("UPDATE department API is called");
        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(request);
        return new ResponseEntity<>(updatedDepartmentDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Integer id) {
        System.out.println("DELETE department API is called");
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Department deleted successfully.", HttpStatus.OK);
    }
}
