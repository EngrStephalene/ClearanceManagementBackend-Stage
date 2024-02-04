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

    private String birthday;

    private String gender;

    private String facultyOffice;

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

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyOffice() {
        return facultyOffice;
    }

    public void setFacultyOffice(String facultyOffice) {
        this.facultyOffice = facultyOffice;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
