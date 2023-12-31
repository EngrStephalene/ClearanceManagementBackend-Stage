package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.ClearanceDto;
import com.clearance.management.backend.dto.ClearanceDtoWithStudentName;
import com.clearance.management.backend.dto.ClearanceWithFacultyDTO;
import com.clearance.management.backend.dto.StudentClearanceHeaderDTO;
import com.clearance.management.backend.request.ClearanceRequest;
import com.clearance.management.backend.request.RejectClearanceRequest;

import java.util.List;

public interface ClearanceService {
    public List<ClearanceDto> addClearance(ClearanceRequest request);

    public List<ClearanceDtoWithStudentName> getAllClearanceRequest();

    public List<ClearanceWithFacultyDTO> getClearanceByStudentId(Integer studentId);

    public void deleteClearanceByStudentId(Integer studentId);

    public ClearanceDto markAsApprove(Integer id);

    public ClearanceDto markAsReject(RejectClearanceRequest request);

    public List<ClearanceDtoWithStudentName> getClearanceByFacultyId(Integer facultyId);

    public StudentClearanceHeaderDTO getStudentInformationForHeader(Integer userId);
}
