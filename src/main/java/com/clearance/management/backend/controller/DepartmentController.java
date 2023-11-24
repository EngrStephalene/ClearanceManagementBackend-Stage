package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.DepartmentDto;
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
@CrossOrigin("clearance-management-frontend-stage.vercel.app")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY_HEAD')")
    @PostMapping("/add")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto request) {
        System.out.println("ADD department API is called");
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(request);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY_HEAD', 'FACULTY')")
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Integer id) {
        System.out.println("GET department API is called");
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return  new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'FACULTY', 'ROLE_FACULTY_HEAD'," +
            " 'ROLE_TREASURER', 'ROLE_CHAIRMAN', 'ROLE_SG_ADVISER', 'ROLE_CAMPUS_MINISTRY', " +
            "'ROLE_GUIDANCE_OFFICE', 'ROLE_LIBRARIAN', 'ROLE_DISPENSARY', " +
            "'ROLE_PROPERTY_CUSTODIAN', 'ROLE_PREFECT_DISCIPLINE', 'ROLE_REGISTRAR', 'ROLE_FINANCE')")
    @GetMapping("/")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        System.out.println("GET ALL department API is called");
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartment();
        return new ResponseEntity<>(departmentDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','FACULTY_HEAD')")
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto request, @PathVariable("id") Integer id) {
        System.out.println("UPDATE department API is called");
        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(request, id);
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
