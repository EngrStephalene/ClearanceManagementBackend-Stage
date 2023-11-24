package com.clearance.management.backend.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "clearances")
public class Clearance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "log_date")
    private java.util.Date logDate;

    @Column(name = "reason", nullable = false)

    private String reason;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "faculty_id", nullable = false)
    private String facultyId;

    @Column(name = "approved_date")
    private Date approvedDate;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="clearance_faculty_junction",
            joinColumns = {@JoinColumn(name="clearance_id")},
            inverseJoinColumns = {@JoinColumn(name="faculty_id")}
    )
    private Set<Faculty> approvers;

    private String remarks;

    @Column(name = "approver_name")
    private String approverName;

    public Clearance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Faculty> getApprovers() {
        return approvers;
    }

    public void setApprovers(Set<Faculty> approvers) {
        this.approvers = approvers;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
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
}
