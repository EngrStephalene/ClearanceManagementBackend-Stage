package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.ViolationDto;
import com.clearance.management.backend.dto.ViolationRequest;
import com.clearance.management.backend.dto.ViolationWithStudentNameDto;

import java.util.List;

public interface ViolationService {
    public ViolationDto addViolation(ViolationRequest request, String studentNumber);
    public ViolationDto getViolation(Integer violationId);
    public List<ViolationWithStudentNameDto> getAllViolation();
    public ViolationDto updateViolation(ViolationRequest request, Integer id);
    public void deleteToDo(Integer id);
    public ViolationDto completeViolation(Integer id);
    public List<ViolationDto> getViolationByStudentNumber(String studentNumber);

    //STUDENT ID IS REFERRED TO AS STUDENT NUMBER IN STUDENT TABLE
    public List<ViolationDto> getStudentViolationByStudentId(String studentId);
}
