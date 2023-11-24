package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.FacultyDto;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.dto.ViolationDto;

public interface EmailService {
    public void studentAccountVerifEmail(String name, String email, String username, String password);
    public void facultyAccountVerifEmail(String name, String email, String username, String password);
    public void studentInformationUpdatedEmail(String name, String email, StudentDto studentDto);
    public void facultyInformationUpdatedEmail(String name, String email, FacultyDto facultyDto);

    //FOR VIOLATION EMAILS
    public void newStudentViolationEmail(ViolationDto violationDto, FacultyDto facultyDto, StudentDto studentDto);
}
