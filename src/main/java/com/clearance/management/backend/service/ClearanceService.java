package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.ClearanceDto;
import com.clearance.management.backend.request.ClearanceRequest;

import java.util.List;

public interface ClearanceService {
    public List<ClearanceDto> addClearance(ClearanceRequest request);

    public List<ClearanceDto> getAllClearanceRequest();

    public List<ClearanceDto> getClearanceByStudentId(Integer studentId);

    public void deleteClearanceByStudentId(Integer studentId);

    public ClearanceDto markAsApprove(Integer id);

    public ClearanceDto markAsReject(Integer id, String remarks);
}
