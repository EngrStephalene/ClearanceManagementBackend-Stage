package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.SubjectDto;
import com.clearance.management.backend.request.SubjectRequest;

import java.util.List;

public interface SubjectService {

    public List<SubjectDto> getAllSubject();
    public List<SubjectDto> getSubjectByDeptId(Integer deptId);
    public SubjectDto addSubject(SubjectDto request);
    public SubjectDto addSubjectByDept(SubjectRequest request);
    public SubjectDto updateSubject(SubjectRequest request, Integer id);
    public void deleteSubject(Integer id);
}
