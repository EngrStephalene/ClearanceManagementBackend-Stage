package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.Student;

import java.util.Date;

public class ViolationDto {

    private Integer id;
    private String description;
    private String actionItem;
    private Date logDate;
    private boolean completed;

    private String studentId;

    private Date updatedDate;

    private Integer facultyId;

    public ViolationDto() {
    }

    public ViolationDto(boolean complete, String description, String actionItem,Date logDate) {
        this.description = description;
        this.actionItem = actionItem;
        this.logDate = logDate;
        this.completed = complete;
    }

    public ViolationDto(boolean completed,String description, String actionItem) {
        this.completed = completed;
        this.description = description;
        this.actionItem = actionItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionItem() {
        return actionItem;
    }

    public void setActionItem(String actionItem) {
        this.actionItem = actionItem;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
