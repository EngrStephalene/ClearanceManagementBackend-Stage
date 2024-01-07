package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.FacultyDto;
import com.clearance.management.backend.request.AppUserRoleRequest;
import com.clearance.management.backend.request.FacultyRequest;
import com.clearance.management.backend.request.UpdateFacultyRequest;
import com.clearance.management.backend.service.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
@AllArgsConstructor
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PostMapping("/add")
    public ResponseEntity<FacultyDto> addFaculty(@RequestBody FacultyDto request) {
        System.out.println("ADD faculty API is called");
        FacultyDto savedFaculty = facultyService.addFaculty(request);
        return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/head/add")
    public ResponseEntity<FacultyDto> addFacultyHead(@RequestBody FacultyRequest request) {
        System.out.println("ADD FACULTY HEAD API is called.");
        FacultyDto savedFaculty = facultyService.addFacultyHead(request);
        return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/")
    public ResponseEntity<List<FacultyDto>> getAllFaculty() {
        System.out.println("GET ALL faculty api is called.");
        List<FacultyDto> facultyList = facultyService.getAllFaculty();
        return new ResponseEntity<>(facultyList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/get")
    public ResponseEntity<FacultyDto> getFacultyByFacultyNumber(@RequestParam(name = "facultyNumber") String facultyNumber) {
        System.out.println("GET FACULTY BY FACULTY NUMBER api is called.");
        FacultyDto faculty = facultyService.getFacultyByFacultyNumber(facultyNumber);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/info/{userId}")
    public ResponseEntity<FacultyDto> getFacultyInfoByRole(@PathVariable("userId") String userId) {
        System.out.print("Get faculty information based on app user info api is called.");
        FacultyDto facultyDto = facultyService.getFacultyInfoBasedOnRole(userId);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable("id") Integer id, @RequestBody UpdateFacultyRequest request) {
        System.out.println("UPDATE faculty api is called.");
        FacultyDto updatedFaculty = facultyService.updateFaculty(request, id);
        return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/getFaculty/{username}")
    public ResponseEntity<FacultyDto> getFacultyByUsername(@PathVariable(name = "username") String username) {
        System.out.println("GET Faculty by user id api is called.");
        FacultyDto facultyDto = facultyService.getFacultyByUsername(username);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable("id") Integer id) {
        System.out.println("DELETE faculty api is called.");
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>("Faculty deleted successfully.", HttpStatus.OK);
    }
}
