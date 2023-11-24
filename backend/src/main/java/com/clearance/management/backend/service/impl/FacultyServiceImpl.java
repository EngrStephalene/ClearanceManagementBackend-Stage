package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.dto.FacultyDto;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.request.AppUserRoleRequest;
import com.clearance.management.backend.request.FacultyRequest;
import com.clearance.management.backend.service.AuthenticationService;
import com.clearance.management.backend.service.EmailService;
import com.clearance.management.backend.service.FacultyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public FacultyDto addFaculty(FacultyDto request) {
        Faculty faculty = modelMapper.map(request, Faculty.class);

        AppUserDto appUserDto = authenticationService.registerFaculty(faculty.getFirstName(), faculty.getLastName(), faculty.getEmail());
        ApplicationUser facultyAppDetl = modelMapper.map(appUserDto, ApplicationUser.class);

        //Save to database
        faculty.setApplicationUser(facultyAppDetl);
        Faculty savedFaculty = facultyRepository.save(faculty);

        //Send an email to the faculty with his/her credentials
        StringBuffer str = new StringBuffer();
        str.append(savedFaculty.getFirstName());
        str.append(" ");
        str.append(savedFaculty.getLastName());
        String facultyName = str.toString();
        String pass = authenticationService.getPassword();
        System.out.println(pass);
        emailService.facultyAccountVerifEmail(facultyName, savedFaculty.getEmail(), facultyAppDetl.getUsername(), pass);

        return modelMapper.map(savedFaculty, FacultyDto.class);
    }

    @Override
    public FacultyDto addFacultyHead(FacultyRequest request) {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setFacultyNumber(request.getFacultyNumber());
        facultyDto.setFirstName(request.getFirstName());
        facultyDto.setMiddleName(request.getMiddleName());
        facultyDto.setLastName(request.getLastName());
        facultyDto.setEmail(request.getEmail());
        facultyDto.setAddress(request.getAddress());

        AppUserDto appUserDto = authenticationService.registerFacultyHead(facultyDto.getFirstName(), facultyDto.getLastName(), facultyDto.getEmail(), request.getRole());
        ApplicationUser facultyHeadDetl = modelMapper.map(appUserDto, ApplicationUser.class);

        Faculty faculty = modelMapper.map(facultyDto, Faculty.class);
        faculty.setApplicationUser(facultyHeadDetl);
        Faculty savedFaculty = facultyRepository.save(faculty);

        //Send an email to the faculty head with his/her credentials
        StringBuffer str = new StringBuffer();
        str.append(savedFaculty.getFirstName());
        str.append(" ");
        str.append(savedFaculty.getLastName());
        String facultyName = str.toString();
        String pass = authenticationService.getPassword();
        System.out.println(pass);
        emailService.facultyAccountVerifEmail(facultyName, savedFaculty.getEmail(), facultyHeadDetl.getUsername(), pass);

        return modelMapper.map(savedFaculty, FacultyDto.class);
    }

    @Override
    public List<FacultyDto> getAllFaculty() {
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList
                .stream()
                .map((faculty) -> modelMapper.map(faculty, FacultyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDto getFacultyByFacultyNumber(String facultyNumber) {
        Faculty faculty = facultyRepository.findFacultyByFacultyNumber(facultyNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty with faculy number " + facultyNumber + " not found."));
        return modelMapper.map(faculty, FacultyDto.class);
    }

    @Override
    public FacultyDto updateFaculty(FacultyDto request, Integer id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty with id " + id + " not found."));
        faculty.setFacultyNumber(request.getFacultyNumber());
        faculty.setEmail(request.getEmail());
        faculty.setFirstName(request.getFirstName());
        faculty.setMiddleName(request.getMiddleName());
        faculty.setLastName(request.getLastName());
        faculty.setAddress(request.getAddress());
        Faculty updatedFaculty = facultyRepository.save(faculty);
        FacultyDto updatedFacultyDto = modelMapper.map(updatedFaculty, FacultyDto.class);

        //Send an email to faculty with his/her updated information
        StringBuffer str = new StringBuffer();
        str.append(updatedFaculty.getFirstName());
        str.append(" ");
        str.append(updatedFaculty.getLastName());
        String facultyName = str.toString();
        emailService.facultyInformationUpdatedEmail(facultyName, updatedFaculty.getEmail(), updatedFacultyDto);

        return updatedFacultyDto;
    }

    @Override
    public void deleteFaculty(Integer id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty with faculty id: " + id + "not found"));
        facultyRepository.deleteById(id);
    }

    @Override
    public FacultyDto getFacultyByUsername(String username) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Application User with username " + username + " not found"));
        Faculty faculty = facultyRepository.findFacultyByUserId(applicationUser)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty with username: " + username + " not found."));
        return modelMapper.map(faculty, FacultyDto.class);
    }

    @Override
    public FacultyDto getFacultyInfoBasedOnRole(String userId) {
        ApplicationUser applicationUser = applicationUserRepository.findById(Integer.valueOf(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Application user with id " + userId + " not found"));
        Faculty faculty = facultyRepository.findFacultyByUserId(applicationUser)
                .orElseThrow(() -> new ResourceNotFoundException("User information with id " + userId + " not found."));
        return modelMapper.map(faculty, FacultyDto.class);
    }
}
