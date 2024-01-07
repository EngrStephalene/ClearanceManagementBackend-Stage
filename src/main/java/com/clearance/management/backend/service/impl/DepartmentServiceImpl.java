package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.DepartmentDto;
import com.clearance.management.backend.entity.Department;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.DepartmentRepository;
import com.clearance.management.backend.request.UpdateDepartmentRequest;
import com.clearance.management.backend.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto addDepartment(DepartmentDto request) {
        Department department = modelMapper.map(request, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartment(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departmentList =  departmentRepository.findAll();
        return departmentList
                .stream()
                .map((department) -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(UpdateDepartmentRequest request) {
        Department department = departmentRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getId()));
        department.setDepartmentName(request.getDepartmentName());
        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        departmentRepository.deleteById(id);
    }
}
