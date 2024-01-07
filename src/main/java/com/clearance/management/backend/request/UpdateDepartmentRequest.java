package com.clearance.management.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepartmentRequest {
    private Integer id;
    private String departmentName;

    public UpdateDepartmentRequest() {
    }
}
