package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.dto.StudentDto;
import com.clearance.management.backend.dto.UserProfileDto;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Role;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.StudentRepository;
import com.clearance.management.backend.request.UpdatePasswordRequest;
import com.clearance.management.backend.request.UpdateUserProfileRequest;
import com.clearance.management.backend.request.UserRequest;
import com.clearance.management.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean verifyPassword(UserRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Application User with username " + username + " not found"));
        return passwordEncoder.matches(password, applicationUser.getPassword());
    }

    @Override
    public AppUserDto updatePassword(UpdatePasswordRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String newPassword = request.getNewPassword();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Application User with username " + username + " not found"));
        if(passwordEncoder.matches(password, applicationUser.getPassword())) {
            applicationUser.setPassword(passwordEncoder.encode(newPassword));
            applicationUserRepository.save(applicationUser);
        }
        System.out.println(applicationUser.getPassword());
        return modelMapper.map(applicationUser, AppUserDto.class);
    }

    @Override
    public UserProfileDto updateUserInformation(UpdateUserProfileRequest request) {
        String roleFromRequest = request.getRole();
        UserProfileDto userProfileDto = new UserProfileDto();
        ApplicationUser applicationUser = applicationUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with user ID: " + request.getUserId() + " not found."));
        userProfileDto.setUserId(applicationUser.getId());
        userProfileDto.setRole(request.getRole());
        if(roleFromRequest != null && roleFromRequest.equals("ROLE_STUDENT")) {
            Student student = studentRepository.findStudentByUserId(applicationUser)
                    .orElseThrow(() -> new ResourceNotFoundException("Student with user ID: " + request.getUserId() + " not found."));
            student.setFirstName(request.getFirstName());
            student.setMiddleName(request.getMiddleName());
            student.setLastName(request.getLastName());
            student.setAddress(request.getAddress());
            student.setEmail(request.getEmail());
            studentRepository.save(student);
            userProfileDto.setFirstName(student.getFirstName());
            userProfileDto.setMiddleName(student.getMiddleName());
            userProfileDto.setAddress(student.getAddress());
        } else {
            Faculty faculty = facultyRepository.findFacultyByUserId(applicationUser)
                    .orElseThrow(() -> new ResourceNotFoundException("Faculty with user ID: " + request.getUserId() + " not found."));
            faculty.setFirstName(request.getFirstName());
            faculty.setMiddleName(request.getMiddleName());
            faculty.setLastName(request.getLastName());
            faculty.setAddress(request.getAddress());
            faculty.setEmail(request.getEmail());
            facultyRepository.save(faculty);
            userProfileDto.setFirstName(faculty.getFirstName());
            userProfileDto.setMiddleName(faculty.getMiddleName());
            userProfileDto.setAddress(faculty.getAddress());
        }
        return userProfileDto;
    }

    @Override
    public String getRoleByUserId(Integer id) {
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
}
