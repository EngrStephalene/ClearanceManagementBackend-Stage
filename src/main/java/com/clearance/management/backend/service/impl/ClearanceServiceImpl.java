package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.*;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Clearance;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.ClearanceRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.StudentRepository;
import com.clearance.management.backend.request.ClearanceRequest;
import com.clearance.management.backend.request.RejectClearanceRequest;
import com.clearance.management.backend.service.ClearanceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

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
                String approveName = str.toString();
                clearanceDto.setApproverName(approveName);
                Clearance clearance = modelMapper.map(clearanceDto, Clearance.class);
                Clearance savedClearance = clearanceRepository.save(clearance);
                createdClearances.add(modelMapper.map(savedClearance, ClearanceDto.class));
            }
        }

        return createdClearances;
    }

    @Override
    public List<ClearanceDtoWithStudentName> getAllClearanceRequest() {
        List<Clearance> clearanceList = clearanceRepository.findAll();
        List<ClearanceDtoWithStudentName> clearanceDtoWithStudentNameList = new ArrayList<ClearanceDtoWithStudentName>();
        List<ClearanceDto> clearanceDtoList = clearanceList
                .stream()
                .map((clearance) -> modelMapper.map(clearance, ClearanceDto.class))
                .toList();
        for(ClearanceDto clearanceDto: clearanceDtoList) {
            ClearanceDtoWithStudentName clearanceDtoWithStudentName = buildClearanceWithStudentName(clearanceDto);
            clearanceDtoWithStudentNameList.add(clearanceDtoWithStudentName);
        }
        return clearanceDtoWithStudentNameList;
    }

    private ClearanceDtoWithStudentName buildClearanceWithStudentName(ClearanceDto clearance) {
        ClearanceDtoWithStudentName clearanceDtoWithStudentName = new ClearanceDtoWithStudentName();
        ApplicationUser applicationUser = applicationUserRepository.findById(Integer.parseInt(clearance.getFacultyId()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        Faculty faculty = facultyRepository.findFacultyByUserId(applicationUser)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found."));
        if(clearance.getId() != null) {
            clearanceDtoWithStudentName.setId(clearance.getId());
        }
        if(clearance.getStudentId() != null) {
            clearanceDtoWithStudentName.setStudentId(clearance.getStudentId());
        }
        if(clearance.getFacultyId() != null){
            clearanceDtoWithStudentName.setFacultyId(clearance.getFacultyId());
        }
        if(clearance.getApproverName() != null) {
            clearanceDtoWithStudentName.setApproverName(clearance.getApproverName());
        }
        if(clearance.getLogDate() != null) {
            clearanceDtoWithStudentName.setLogDate(clearance.getLogDate());
        }
        if(clearance.getApprovedDate() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd , yyyy");
            clearanceDtoWithStudentName.setApprovedDate(formatter.format(clearance.getApprovedDate()));
        }
        if(clearance.getReason() != null) {
            clearanceDtoWithStudentName.setReason(clearance.getReason());
        }
        if(clearance.getStatus() != null) {
            clearanceDtoWithStudentName.setStatus(clearance.getStatus());
        }
        if(clearance.getRemarks() != null) {
            clearanceDtoWithStudentName.setRemarks(clearance.getRemarks());
        }
        clearanceDtoWithStudentName.setOffice(faculty.getFacultyOffice());

        Student student = studentRepository.findById(Integer.parseInt(clearance.getStudentId()))
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + clearance.getStudentId() + " not found."));
        StringBuilder sb = new StringBuilder();
        sb.append(student.getFirstName());
        sb.append(" ");
        sb.append(student.getLastName());
        clearanceDtoWithStudentName.setStudentName(sb.toString());
        return clearanceDtoWithStudentName;
    }

    @Override
    public List<ClearanceDtoWithStudentName> getClearanceByFacultyId(Integer facultyId) {
        List<Clearance> clearanceList = clearanceRepository.findClearanceByFacultyId(facultyId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance for faculty " + facultyId + " not found."));
        List<ClearanceDtoWithStudentName> clearanceDtoWithStudentNameList = new ArrayList<ClearanceDtoWithStudentName>();
        List<ClearanceDto> clearanceDtoList = clearanceList
                .stream()
                .map((clearance) -> modelMapper.map(clearance, ClearanceDto.class))
                .toList();
        for(ClearanceDto clearanceDto: clearanceDtoList) {
            ClearanceDtoWithStudentName clearanceDtoWithStudentName = buildClearanceWithStudentName(clearanceDto);
            clearanceDtoWithStudentNameList.add(clearanceDtoWithStudentName);
        }
        return clearanceDtoWithStudentNameList;
    }

    @Override
    public List<ClearanceWithFacultyDTO> getClearanceByStudentId(Integer studentId) {
        List<Clearance> clearanceList = clearanceRepository.findClearanceByStudentId(studentId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance for student " + studentId + " not found"));
        List<ClearanceWithFacultyDTO> clearanceWithFacultyDTOList = new ArrayList<>();
        for(Clearance clearance: clearanceList) {
            ClearanceWithFacultyDTO clearanceWithFacultyDTO = new ClearanceWithFacultyDTO();
            ApplicationUser applicationUser = applicationUserRepository.findById(Integer.parseInt(clearance.getFacultyId()))
                    .orElseThrow(() -> new ResourceNotFoundException("User not found."));
            Faculty faculty = facultyRepository.findFacultyByUserId(applicationUser)
                    .orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));
            if(!faculty.getFacultyOffice().equals("Admin")) {
                SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd , yyyy");
                if(clearance.getApprovedDate() != null) {
                    clearanceWithFacultyDTO.setApprovedDate(formatter.format(clearance.getApprovedDate()));
                }
                clearanceWithFacultyDTO.setFacultyId(clearance.getFacultyId());
                clearanceWithFacultyDTO.setOffice(faculty.getFacultyOffice());
                clearanceWithFacultyDTO.setStudentId(clearance.getStudentId());
                clearanceWithFacultyDTO.setRemarks(clearance.getRemarks());
                clearanceWithFacultyDTO.setStatus(clearance.getStatus());
                clearanceWithFacultyDTO.setApproverName(clearance.getApproverName());
                clearanceWithFacultyDTO.setLogDate(clearance.getLogDate());
                clearanceWithFacultyDTO.setReason(clearance.getReason());
                clearanceWithFacultyDTOList.add(clearanceWithFacultyDTO);
            }
        }
        return clearanceWithFacultyDTOList;
    }

    private String getRoleByUserId(Integer id) {
        ApplicationUser applicationUser = applicationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with user ID: " + id + " not found."));
        Collection<? extends GrantedAuthority> authorities = applicationUser.getAuthorities();
        String role = "";
        String roleConcat = "";
        for(GrantedAuthority auth: authorities) {
            role = auth.getAuthority();
            break;
        }
        roleConcat = switch (role) {
            case "ROLE_FACULTY" -> "faculty";
            case "ROLE_FACULTY_HEAD" -> "facultyHead";
            case "ROLE_TREASURER" -> "treasurer";
            case "ROLE_CHAIRMAN" -> "chairman";
            case "ROLE_SG_ADVISER" -> "sgAdviser";
            case "ROLE_CAMPUS_MINISTRY" -> "campusMinistry";
            case "ROLE_GUIDANCE_OFFICE" -> "guidanceOffice";
            case "ROLE_LIBRARIAN" -> "libraryIncharge";
            case "ROLE_DISPENSARY" -> "dispensaryIncharge";
            case "ROLE_PROPERTY_CUSTODIAN" -> "propertyCustodian";
            case "ROLE_PREFECT_DISCIPLINE" -> "prefectOfDiscipline";
            case "ROLE_REGISTRAR" -> "schoolRegistrar";
            case "ROLE_FINANCE" -> "finance";
            default -> roleConcat;
        };
        return roleConcat;
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
    public ClearanceDto markAsReject(RejectClearanceRequest request) {
        Clearance clearance = clearanceRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance with id " + request + " not found"));
        ClearanceDto clearanceForUpdate = modelMapper.map(clearance, ClearanceDto.class);
        String status = "Rejected";
        clearanceForUpdate.setStatus(status);
        clearanceForUpdate.setRemarks(request.getRemarks());
        Clearance clearanceForSave = modelMapper.map(clearanceForUpdate, Clearance.class);
        Clearance savedClearance = clearanceRepository.save(clearanceForSave);
        return modelMapper.map(savedClearance, ClearanceDto.class);
    }

    @Override
    public StudentClearanceHeaderDTO getStudentInformationForHeader(Integer userId) {
        StudentClearanceHeaderDTO studentClearanceHeaderDTO = new StudentClearanceHeaderDTO();
        ApplicationUser applicationUser = applicationUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found."));
        Student student = studentRepository.findStudentByUserId(applicationUser)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));
        StringBuilder sb = new StringBuilder();
        sb.append(student.getFirstName());
        sb.append(" ");
        sb.append(student.getLastName());
        String studentName = sb.toString();
        studentClearanceHeaderDTO.setStudentName(studentName);
        studentClearanceHeaderDTO.setYearLevel(student.getYearLevel());
        List<Clearance> clearanceList = clearanceRepository.findClearanceByStudentId(userId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Clearance with student ID: " + userId.toString() + " not found."));
        for(Clearance clearance : clearanceList) {
            if(clearance.getReason() != null && !clearance.getReason().isEmpty()) {
                studentClearanceHeaderDTO.setPurpose(clearance.getReason());
                break;
            }
        }
        return studentClearanceHeaderDTO;
    }
}
