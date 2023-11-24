package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.entity.Violation;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.StudentRepository;
import com.clearance.management.backend.service.AuthenticationService;
import com.clearance.management.backend.service.EmailService;
import com.clearance.management.backend.service.StudentService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public StudentDto addStudent(StudentDto request) {
        //SET THE VIOLATION OBJECT TO NULL
        request.setViolations(null);
        Student student = modelMapper.map(request, Student.class);

        //Set the application user information
        AppUserDto appUserDto =  authenticationService.registerStudent(student.getFirstName(), student.getLastName(), student.getEmail());
        ApplicationUser studentAppDetl = modelMapper.map(appUserDto, ApplicationUser.class);

        //Save to database
        student.setApplicationUser(studentAppDetl);
        Student savedStudent = studentRepository.save(student);

        //Send an email to student with his user credentials
        StringBuffer str = new StringBuffer();
        str.append(savedStudent.getFirstName());
        str.append(" ");
        str.append(savedStudent.getLastName());
        String studentName = str.toString();
        String pass = authenticationService.getPassword();
        System.out.println(pass);
        emailService.studentAccountVerifEmail(studentName, savedStudent.getEmail(), studentAppDetl.getUsername(), pass);

        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return studentList
                .stream()
                .map((student) -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentByStudentNumber(String studentNumber) {
        Student student = studentRepository.findStudentByStudentNumber(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student with student id: " + studentNumber + " not found."));
        return  modelMapper.map(student, StudentDto.class);
    }

    /**
     * CAN BE IMPROVE TO ONLY UPDATE VARIABLES THAT ARE UPDATED FROM THE REQUEST
     * @param request
     * @param id
     * @return
     */
    @Override
    public StudentDto updateStudent(StudentDto request, Integer id) {
       Student student = studentRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
       student.setStudentNumber(request.getStudentNumber());
       student.setEmail(request.getEmail());
       student.setFirstName(request.getFirstName());
       student.setMiddleName(request.getMiddleName());
       student.setLastName(request.getLastName());
       student.setAddress(request.getAddress());
       Student updatedStudent = studentRepository.save(student);
       StudentDto updatedStudentDto =  modelMapper.map(updatedStudent, StudentDto.class);

       //Send an email to student with his/her information updated information
       StringBuffer str = new StringBuffer();
       str.append(updatedStudent.getFirstName());
       str.append(" ");
       str.append(updatedStudent.getLastName());
       String studentName = str.toString();
       emailService.studentInformationUpdatedEmail(studentName, updatedStudent.getEmail(), updatedStudentDto);

       return updatedStudentDto;
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudentInfoBasedOnUserId(Integer userId) {
        ApplicationUser applicationUser = applicationUserRepository.findById(Integer.valueOf(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Application user with id " + userId + " not found"));
        Student student = studentRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + userId + " not found."));
        return modelMapper.map(student, StudentDto.class);
    }
}
