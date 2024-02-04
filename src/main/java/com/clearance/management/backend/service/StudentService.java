package com.clearance.management.backend.service;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.request.UpdateStudentRequest;

import java.util.List;

public interface StudentService {

    public StudentDto addStudent(StudentDto request);
    public List<StudentDto> getAllStudent();
    public StudentDto getStudentByStudentNumber(String studentNumber);
    public StudentDto updateStudent(UpdateStudentRequest request);
    public void deleteStudent(Integer id);
    public StudentDto getStudentInfoBasedOnUserId(Integer userId);
    public String getStudentNumberByUserId(Integer userId);
    public String getStudentNameByUserId(Integer userId);
    public List<StudentDto> addAllStudents(List<StudentDto> request);
}
