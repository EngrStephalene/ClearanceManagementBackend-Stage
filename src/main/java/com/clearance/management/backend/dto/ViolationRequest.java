package com.clearance.management.backend.dto;

public class ViolationRequest {
    private Integer id;
    private String description;
    private String actionItem;
    private boolean completed;
    private Integer facultyId;

    public ViolationRequest() {
    }

    public ViolationRequest(String description, String actionItem, boolean completed, Integer facultyId, Integer studentId) {
        this.description = description;
        this.actionItem = actionItem;
        this.completed = completed;
        this.facultyId = facultyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
}
