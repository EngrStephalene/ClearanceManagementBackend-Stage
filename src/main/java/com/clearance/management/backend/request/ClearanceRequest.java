package com.clearance.management.backend.request;

public class ClearanceRequest {
    String reason;
    String studentId;

    public ClearanceRequest() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
