package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.StudentClearanceHeaderDTO;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.request.UpdateStudentRequest;
import com.clearance.management.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/")
    public ResponseEntity<List<StudentDto>> getAllStudent() {
        System.out.println("GET ALL student api is called.");
        List<StudentDto> studentList = studentService.getAllStudent();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    /**
     * Student number is equivalent to the student's ID number
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/get")
    public ResponseEntity<StudentDto> getStudentByStudentNumber(@RequestParam(name = "studentNumber") String studentNumber) {
        System.out.println("GET STUDENT BY STUDENT NUMBER api is called.");
        StudentDto student = studentService.getStudentByStudentNumber(studentNumber);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/get-studentNumber/{userId}")
    public ResponseEntity<String> getStudentNumberByUserId(@PathVariable("userId") Integer userId) {
        System.out.println("GET STUDENT NUMBER BY USER ID API IS CALLED.");
        String studentNumber = studentService.getStudentNumberByUserId(userId);
        return new ResponseEntity<>(studentNumber, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/get-studentName/{userId}")
    public ResponseEntity<String> getStudentFullnameByUserId(@PathVariable("userId") Integer userId) {
        System.out.println("GET STUDENT FULL NAME BY USER ID API IS CALLED.");
        String studentName = studentService.getStudentNameByUserId(userId);
        return new ResponseEntity<>(studentName, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Integer id) {
        System.out.println("Get student by id API is called.");
        StudentDto studentDto = studentService.getStudentInfoBasedOnUserId(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'FACULTY_HEAD'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto request) {
        System.out.print("ADD student api is called.");
        StudentDto savedStudent = studentService.addStudent(request);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody UpdateStudentRequest request) {
        System.out.println("UPDATE student api is called.");
        StudentDto updatedStudent = studentService.updateStudent(request);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
        System.out.println("DELETE student api is called.");
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully.", HttpStatus.OK);
    }
}
