package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.Subject;

import java.util.Set;

public class DepartmentDto {
    private Integer id;
    private String departmentName;

    private Set<Subject> subjects;

    public DepartmentDto() {
    }

    public DepartmentDto(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentDto(String departmentName, Set<Subject> subjects) {
        this.departmentName = departmentName;
        this.subjects = subjects;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
