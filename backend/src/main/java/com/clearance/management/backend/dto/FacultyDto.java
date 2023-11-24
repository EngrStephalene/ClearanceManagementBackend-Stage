package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.ApplicationUser;

public class FacultyDto {
    private Integer facultyId;

    private String facultyNumber;

    private String firstName;

    private String middleName;

    private String lastName;

    private String address;

    private String email;

    public FacultyDto() {
    }

    public Integer getId() {
        return facultyId;
    }

    public void setId(Integer id) {
        this.facultyId = id;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
