package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.FacultyDto;
import com.clearance.management.backend.request.AppUserRoleRequest;
import com.clearance.management.backend.request.FacultyRequest;
import com.clearance.management.backend.request.UpdateFacultyRequest;

import java.util.List;

public interface FacultyService {
    public FacultyDto addFaculty(FacultyDto request);

    public FacultyDto addFacultyHead(FacultyRequest request);

    public List<FacultyDto> getAllFaculty();

    public FacultyDto getFacultyByFacultyNumber(String facultyNumber);

    public FacultyDto updateFaculty(UpdateFacultyRequest request, Integer id);

    public void deleteFaculty(Integer id);

    public FacultyDto getFacultyByUsername(String username);

    FacultyDto getFacultyInfoBasedOnRole(String userId);
}
