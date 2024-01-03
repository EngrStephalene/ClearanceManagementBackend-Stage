package com.clearance.management.backend.request;

public class RejectClearanceRequest {
    private Integer id;
    private String remarks;

    public RejectClearanceRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
