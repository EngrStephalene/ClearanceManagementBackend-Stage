package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto addStudent(StudentDto request);
    public List<StudentDto> getAllStudent();
    public StudentDto getStudentByStudentNumber(String studentNumber);
    public StudentDto updateStudent(StudentDto request, Integer id);
    public void deleteStudent(Integer id);
    public StudentDto getStudentInfoBasedOnUserId(Integer userId);
    public String getStudentNumberByUserId(Integer userId);
    public String getStudentNameByUserId(Integer userId);
}
