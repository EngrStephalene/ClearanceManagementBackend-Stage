package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Violation;

import java.util.Set;

public class StudentDto {

    private Integer id;
    private String studentNumber;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;

    private Integer userId;
    private Set<Violation> violations;

    public StudentDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<Violation> getViolations() {
        return violations;
    }

    public void setViolations(Set<Violation> violations) {
        this.violations = violations;
    }
}
