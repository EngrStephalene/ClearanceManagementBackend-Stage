package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.ClearanceDto;
import com.clearance.management.backend.entity.Clearance;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ClearanceRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.StudentRepository;
import com.clearance.management.backend.request.ClearanceRequest;
import com.clearance.management.backend.service.ClearanceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClearanceServiceImpl implements ClearanceService {
    @Autowired
    private ClearanceRepository clearanceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    private static final String ROLE_TREASURER = "ROLE_TREASURER";
    private static final String ROLE_CHAIRMAN = "ROLE_CHAIRMAN";
    private static final String ROLE_SG_ADVISER = "ROLE_SG_ADVISER";
    private static final String ROLE_CAMPUS_MINISTRY = "ROLE_CAMPUS_MINISTRY";
    private static final String ROLE_GUIDANCE_OFFICE = "ROLE_GUIDANCE_OFFICE";
    private static final String ROLE_LIBRARIAN = "ROLE_LIBRARIAN";
    private static final String ROLE_DISPENSARY = "ROLE_DISPENSARY";
    private static final String ROLE_PROPERTY_CUSTODIAN = "ROLE_PROPERTY_CUSTODIAN";
    private static final String ROLE_PREFECT_DISCIPLINE = "ROLE_PREFECT_DISCIPLINE";
    private static final String ROLE_REGISTRAR = "ROLE_REGISTRAR";
    private static final String ROLE_FINANCE = "ROLE_FINANCE";

    @Override
    public List<ClearanceDto> addClearance(ClearanceRequest request) {
        List<Faculty> faculties = facultyRepository.findAll();
        String status = "Pending";
        Date currentDate = new Date();
        List<ClearanceDto> createdClearances = new ArrayList<>();
        if(!CollectionUtils.isEmpty(faculties)) {
            for(Faculty faculty: faculties) {
                ClearanceDto clearanceDto = new ClearanceDto();
                clearanceDto.setLogDate(currentDate);
                clearanceDto.setStatus(status);
                clearanceDto.setReason(request.getReason());
                clearanceDto.setStudentId(request.getStudentId());
                clearanceDto.setFacultyId(faculty.getId().toString());
                clearanceDto.setRemarks(null);
                StringBuilder str = new StringBuilder();
                str.append(faculty.getFirstName());
                str.append(" ");
                str.append(faculty.getLastName());
                str.append(" ");
                String approverName = str.toString();
                clearanceDto.setApproverName(approverName);
                Clearance clearance = modelMapper.map(clearanceDto, Clearance.class);
                Clearance savedClearance = clearanceRepository.save(clearance);
                createdClearances.add(modelMapper.map(savedClearance, ClearanceDto.class));
            }
        }

        return createdClearances;
    }

    @Override
    public List<ClearanceDto> getAllClearanceRequest() {
        List<Clearance> clearanceList = clearanceRepository.findAll();
        return clearanceList
                .stream()
                .map((clearance) -> modelMapper.map(clearance, ClearanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClearanceDto> getClearanceByStudentId(Integer studentId) {
        List<Clearance> clearanceList = clearanceRepository.findClearanceByStudentId(studentId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance for student " + studentId + " not found"));
        return clearanceList
                .stream()
                .map((clearance) -> modelMapper.map(clearance, ClearanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClearanceByStudentId(Integer studentId) {
        List<Clearance> clearanceList = clearanceRepository.findClearanceByStudentId(studentId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance for student " + studentId + " not found"));
        for(Clearance clearance: clearanceList) {
            clearanceRepository.deleteById(clearance.getId());
        }
    }

    @Override
    public ClearanceDto markAsApprove(Integer id) {
        Clearance clearance = clearanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clearance with id " + id + " not found"));
        ClearanceDto clearanceForUpdate = modelMapper.map(clearance, ClearanceDto.class);
        Date approvedDate = new Date();
        String status = "Approved";
        clearanceForUpdate.setStatus(status);
        clearanceForUpdate.setApprovedDate(approvedDate);
        Clearance clearanceForSave = modelMapper.map(clearanceForUpdate, Clearance.class);
        Clearance savedClearance = clearanceRepository.save(clearanceForSave);
        return modelMapper.map(savedClearance, ClearanceDto.class);
    }

    @Override
    public ClearanceDto markAsReject(Integer id, String remarks) {
        Clearance clearance = clearanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clearance with id " + id + " not found"));
        ClearanceDto clearanceForUpdate = modelMapper.map(clearance, ClearanceDto.class);
        String status = "Rejected";
        clearanceForUpdate.setStatus(status);
        Clearance clearanceForSave = modelMapper.map(clearanceForUpdate, Clearance.class);
        Clearance savedClearance = clearanceRepository.save(clearanceForSave);
        return modelMapper.map(savedClearance, ClearanceDto.class);
    }

    @Override
    public List<ClearanceDto> getClearanceByFacultyId(Integer facultyId) {
        List<Clearance> clearanceList = clearanceRepository.findClearanceByFacultyId(facultyId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance for faculty " + facultyId + " not found."));
        return clearanceList
                .stream()
                .map((clearance) -> modelMapper.map(clearance, ClearanceDto.class))
                .collect(Collectors.toList());
    }
}
