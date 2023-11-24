package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("clearance-management-frontend-stage.vercel.app")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'FACULTY_HEAD'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
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
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY', 'STUDENT')")
    @GetMapping("/get")
    public ResponseEntity<StudentDto> getStudentByStudentNumber(@RequestParam(name = "studentNumber") String studentNumber) {
        System.out.println("GET STUDENT BY STUDENT NUMBER api is called.");
        StudentDto student = studentService.getStudentByStudentNumber(studentNumber);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'FACULTY', 'FACULTY_HEAD'," +
            " 'TREASURER', 'CHAIRMAN', 'SG_ADVISER', 'CAMPUS_MINISTRY', " +
            "'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', 'FINANCE')")
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

    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto request, @PathVariable("id") Integer id) {
        System.out.println("UPDATE student api is called.");
        StudentDto updatedStudent = studentService.updateStudent(request, id);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
        System.out.println("DELETE student api is called.");
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully.", HttpStatus.OK);
    }
}