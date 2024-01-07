package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.Faculty;

import java.util.Date;
import java.util.Set;

public class ClearanceDtoWithStudentName {

    private Integer id;
    private Date logDate;
    private String reason;
    private String status;

    private String studentId;

    private String facultyId;

    private Date approvedDate;

    private Set<Faculty> approvers;

    private String remarks;

    private String approverName;

    private String office;

    String studentName;

    public ClearanceDtoWithStudentName() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Set<Faculty> getApprovers() {
        return approvers;
    }

    public void setApprovers(Set<Faculty> approvers) {
        this.approvers = approvers;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
