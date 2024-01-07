package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.DepartmentDto;
import com.clearance.management.backend.request.UpdateDepartmentRequest;

import java.util.List;

public interface DepartmentService {
    public DepartmentDto addDepartment(DepartmentDto request);
    public DepartmentDto getDepartment(Integer id);
    public List<DepartmentDto> getAllDepartment();
    public DepartmentDto updateDepartment(UpdateDepartmentRequest request);
    public void deleteDepartment(Integer id);
}
