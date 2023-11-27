package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.*;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.entity.Violation;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.StudentRepository;
import com.clearance.management.backend.repository.ViolationRepository;
import com.clearance.management.backend.service.EmailService;
import com.clearance.management.backend.service.ViolationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ViolationServiceImpl implements ViolationService {
    @Autowired
    private ViolationRepository violationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public ViolationDto addViolation(ViolationRequest request, String studentNumber) {
        Student student = studentRepository.findStudentByStudentNumber(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student with student id: " + studentNumber + " not found"));
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);

        Date currentDate = new Date();
        ViolationDto violationDto = new ViolationDto();
        violationDto.setStudentId(student.getStudentNumber());
        violationDto.setLogDate(currentDate);
        violationDto.setUpdatedDate(currentDate);
        violationDto.setFacultyId(request.getFacultyId());
        violationDto.setDescription(request.getDescription());
        violationDto.setActionItem(request.getActionItem());

        Violation violation = modelMapper.map(violationDto, Violation.class);
        Violation savedViolation = violationRepository.save(violation);

        //FETCH THE FACULTY ENTITY BY FACULTY ID
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new ResourceNotFoundException("Faculty with faculty id: " + request.getFacultyId() + " not found"));

        FacultyDto facultyDto = modelMapper.map(faculty, FacultyDto.class);

        //Send an email to student with his violation
        emailService.newStudentViolationEmail(violationDto, facultyDto, studentDto);

        return modelMapper.map(savedViolation, ViolationDto.class);
    }

    @Override
    public ViolationDto getViolation(Integer id) {
       Violation violation = violationRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Violation not found with id: " + id));
       return modelMapper.map(violation, ViolationDto.class);
    }

    @Override
    public List<ViolationWithStudentNameDto> getAllViolation() {
        List<Violation> violationList = violationRepository.findAll();
        List<ViolationDto> violationDtoList = new ArrayList<ViolationDto>();
        List<ViolationWithStudentNameDto> violationWithStudentNameDtoList = new ArrayList<ViolationWithStudentNameDto>();
        violationDtoList = violationList
                .stream()
                .map((violation) -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
        for(ViolationDto violationDto: violationDtoList) {
            ViolationWithStudentNameDto violationWithStudentNameDto = getViolationWithStudentNameDto(violationDto);
            violationWithStudentNameDtoList.add(violationWithStudentNameDto);
        }
        return violationWithStudentNameDtoList;
    }

    private ViolationWithStudentNameDto getViolationWithStudentNameDto(ViolationDto violationDto) {
        ViolationWithStudentNameDto violationWithStudentNameDto = new ViolationWithStudentNameDto();
        violationWithStudentNameDto.setId(violationDto.getId());
        violationWithStudentNameDto.setStudentId(violationDto.getStudentId());
        violationWithStudentNameDto.setDescription(violationDto.getDescription());
        violationWithStudentNameDto.setActionItem(violationDto.getActionItem());
        violationWithStudentNameDto.setLogDate(violationDto.getLogDate());
        violationWithStudentNameDto.setCompleted(violationDto.isCompleted());
        violationWithStudentNameDto.setFacultyId(violationDto.getFacultyId());
        violationWithStudentNameDto.setUpdatedDate(violationDto.getUpdatedDate());
        Student student = studentRepository.findStudentByStudentNumber(violationDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + violationDto.getStudentId() + " not found."));
        StringBuilder sb = new StringBuilder();
        sb.append(student.getFirstName());
        sb.append(" ");
        sb.append(student.getLastName());
        violationWithStudentNameDto.setStudentName(sb.toString());
        return violationWithStudentNameDto;
    }

    @Override
    public ViolationDto updateViolation(ViolationRequest request, Integer id) {
        Violation violation = violationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Violation not found with id: " + id));

        violation.setDescription(request.getDescription());
        violation.setActionItem(request.getActionItem());
        Date updatedDate = new Date();
        violation.setUpdatedDate(updatedDate);

        Violation updatedViolation = violationRepository.save(violation);
        return modelMapper.map(updatedViolation, ViolationDto.class);
    }

    @Override
    public void deleteToDo(Integer id) {
        Violation violation = violationRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Violation not found with id: " + id));
        violationRepository.deleteById(id);
    }

    @Override
    public ViolationDto completeViolation(Integer id) {
        Violation violation = violationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Violation not found with id: " + id));
        violation.setCompleted(Boolean.TRUE);
        Violation updatedViolation = violationRepository.save(violation);
        return modelMapper.map(updatedViolation, ViolationDto.class);
    }

    @Override
    public List<ViolationDto> getViolationByStudentNumber(Integer studentNumber) {
        Student student = studentRepository.findStudentByStudentNumber(studentNumber.toString())
                .orElseThrow(() -> new ResourceNotFoundException("Student with student id: " + studentNumber + " not found."));
        List<Violation> violationList = violationRepository.findViolationByStudentId(studentNumber.toString())
                .orElseThrow(() -> new ResourceNotFoundException("No violation record for student"));
        return violationList
                .stream()
                .map((violation) -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDto> getStudentViolationByStudentId(Integer studentId) {
        List<Violation> violationList = violationRepository.findViolationByStudentId(studentId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("No violation record for student."));
        return violationList
                .stream()
                .map((violation) -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
    }
}