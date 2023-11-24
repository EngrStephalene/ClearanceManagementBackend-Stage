package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.Department;

public class SubjectDto {

    private Integer id;

    private String subjectName;

    private Department department;

    public SubjectDto() {
    }

    public SubjectDto(Department department) {
        this.department = department;
    }

    public SubjectDto(String subjectName, Department department) {
        this.subjectName = subjectName;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
