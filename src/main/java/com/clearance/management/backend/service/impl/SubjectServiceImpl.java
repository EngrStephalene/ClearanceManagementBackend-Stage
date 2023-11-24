package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.DepartmentDto;
import com.clearance.management.backend.dto.SubjectDto;
import com.clearance.management.backend.entity.Department;
import com.clearance.management.backend.entity.Subject;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.DepartmentRepository;
import com.clearance.management.backend.repository.SubjectRepository;
import com.clearance.management.backend.request.SubjectRequest;
import com.clearance.management.backend.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubjectDto> getAllSubject() {
        List<Subject> subjectList =  subjectRepository.findAll();
        return subjectList
                .stream()
                .map((subject) -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectDto> getSubjectByDeptId(Integer deptId) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + deptId));
        List<Subject> subjectList = subjectRepository.findByDepartment(department);
        return subjectList
                .stream()
                .map((subject) -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto addSubject(SubjectDto request) {
        Department departmentFromRequest = request.getDepartment();
        Department department = departmentRepository.findById(departmentFromRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentFromRequest.getId()));
        Subject subject = modelMapper.map(request, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDto.class);
    }

    @Override
    public SubjectDto addSubjectByDept(SubjectRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));
        SubjectDto subjectDtoFromReq = new SubjectDto();
        subjectDtoFromReq.setSubjectName(request.getSubjectName());
        subjectDtoFromReq.setDepartment(department);
        Subject subject = modelMapper.map(subjectDtoFromReq, Subject.class);
        Subject savedSubj = subjectRepository.save(subject);
        return modelMapper.map(savedSubj, SubjectDto.class);
    }

    @Override
    public SubjectDto updateSubject(SubjectRequest request, Integer id) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        subject.setSubjectName(request.getSubjectName());
        subject.setDepartment(department);
        Subject updatedSubject = subjectRepository.save(subject);
        return modelMapper.map(updatedSubject, SubjectDto.class);
    }

    @Override
    public void deleteSubject(Integer id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        subjectRepository.deleteById(id);
    }
}
