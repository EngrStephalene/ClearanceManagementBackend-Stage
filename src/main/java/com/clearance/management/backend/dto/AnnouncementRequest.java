package com.clearance.management.backend.dto;

public class AnnouncementRequest {

    private Integer id;

    private String title;

    private String description;

    private Integer reporterId;

    private Integer reporterRole;

    public AnnouncementRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public Integer getReporterRole() {
        return reporterRole;
    }

    public void setReporterRole(Integer reporterRole) {
        this.reporterRole = reporterRole;
    }
}
