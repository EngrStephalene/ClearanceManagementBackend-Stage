package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.*;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Role;
import com.clearance.management.backend.entity.Student;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.RoleRepository;
import com.clearance.management.backend.repository.StudentRepository;
import com.clearance.management.backend.request.AppUserRoleRequest;
import com.clearance.management.backend.security.JwtTokenProvider;
import com.clearance.management.backend.service.AuthenticationService;
import com.clearance.management.backend.service.EmailService;
import com.clearance.management.backend.utils.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    protected String PASSWORD;
    private static final String ROLE_DEPARTMENT_CHAIRMAN = "ROLE_DEPARTMENT_CHAIRMAN";
    private static final String ROLE_SCHOOL_DIRECTOR = "ROLE_SCHOOL_DIRECTOR";
    private static final String ROLE_COLLEGE_DEAN = "ROLE_COLLEGE_DEAN";
    private static final String ROLE_SG_ADVISER = "ROLE_SG_ADVISER";
    private static final String ROLE_CAMPUS_MINISTRY = "ROLE_CAMPUS_MINISTRY";
    private static final String ROLE_GUIDANCE_OFFICE = "ROLE_GUIDANCE_OFFICE";
    private static final String ROLE_LIBRARIAN = "ROLE_LIBRARIAN";
    private static final String ROLE_DISPENSARY = "ROLE_DISPENSARY";
    private static final String ROLE_PROPERTY_CUSTODIAN = "ROLE_PROPERTY_CUSTODIAN";
    private static final String ROLE_PREFECT_DISCIPLINE = "ROLE_PREFECT_DISCIPLINE";
    private static final String ROLE_REGISTRAR = "ROLE_REGISTRAR";
    private static final String ROLE_FINANCE = "ROLE_FINANCE";


    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private RandomStringGenerator randomStringGenerator;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    private String removeWhitespace(String name) {
        StringBuilder str = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(name);
        String[] nameArray = new String[tokenizer.countTokens()];
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            nameArray[index++] = tokenizer.nextToken();
        }
        System.out.print(nameArray);
        for(String splitStr: nameArray) {
            str.append(splitStr.toLowerCase());
        }
        return str.toString();
    }


    /**
     * CREATE DEFAULT USERNAME AND PASSWORD FOR STUDENT
     * @param firstName
     * @param lastName
     * @param email
     * @return
     */
    @Override
    public AppUserDto registerStudent(String firstName, String lastName, String email) {
        //Build the username using StringBuilder library
        //The username template for student should be: firstname.lastname
        StringBuilder str = new StringBuilder();
        String firstNameNoWhiteSpace = "";
        String lastNameNoWhiteSpace = "";
        if (firstName != null) {
            firstNameNoWhiteSpace = removeWhitespace(firstName);
        }
        str.append(firstNameNoWhiteSpace);
        str.append(".");
        if(lastName != null) {
            lastNameNoWhiteSpace = removeWhitespace(lastName);
        }
        str.append(lastNameNoWhiteSpace);
        String username = str.toString();

        //Generate password using random string generator
        String password = randomStringGenerator.createRandomPassword();
        System.out.println(password);
        setPassword(password);

        //Add set of roles
        Role studentRole = roleRepository.findByAuthority("ROLE_STUDENT").orElseThrow(() -> new ResourceNotFoundException("STUDENT role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(studentRole);

        ApplicationUser applicationUser = new ApplicationUser(username, passwordEncoder.encode(password), email, roles);
        return modelMapper.map(applicationUser, AppUserDto.class);
    }


    /**
     * CREATE DEFAULT USERNAME AND PASSWORD FOR FACULTY
     * @param firstName
     * @param lastName
     * @param email
     * @return
     */
    @Override
    public AppUserDto registerFaculty(String firstName, String lastName, String email) {
        //Build the username using StringBuilder library
        //The username template for faculty should be: lastname.firstname
        //I made it this way to have some difference between faculty and student usernames
        StringBuilder str = new StringBuilder();
        String firstNameNoWhiteSpace = "";
        String lastNameNoWhiteSpace = "";
        if(lastName != null) {
            lastNameNoWhiteSpace = removeWhitespace(lastName);
        }
        str.append(lastNameNoWhiteSpace);
        str.append(".");
        if (firstName != null) {
            firstNameNoWhiteSpace = removeWhitespace(firstName);
        }
        str.append(firstNameNoWhiteSpace);
        String username = str.toString();

        //Generate password using random string generator
        String password = randomStringGenerator.createRandomPassword();
        System.out.println(password);
        setPassword(password);

        //Add set of roles
        Role facultyRole = roleRepository.findByAuthority("ROLE_FACULTY").orElseThrow(() -> new ResourceNotFoundException("FACULTY role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(facultyRole);

        ApplicationUser applicationUser = new ApplicationUser(username, passwordEncoder.encode(password), email, roles);
        return modelMapper.map(applicationUser, AppUserDto.class);
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param role
     * @return
     */
    @Override
    public AppUserDto registerFacultyHead(String firstName, String lastName, String email, String role) {
        //Build the username using StringBuilder library
        //The username template for faculty should be: lastname.firstname
        //I made it this way to have some difference between faculty and student usernames
        StringBuilder str = new StringBuilder();
        String firstNameNoWhiteSpace = "";
        String lastNameNoWhiteSpace = "";
        if(lastName != null) {
            lastNameNoWhiteSpace = removeWhitespace(lastName);
        }
        str.append(lastNameNoWhiteSpace);
        str.append(".");
        if (firstName != null) {
            firstNameNoWhiteSpace = removeWhitespace(firstName);
        }
        str.append(firstNameNoWhiteSpace);
        String username = str.toString();

        //Generate password using random string generator
        String password = randomStringGenerator.createRandomPassword();
        System.out.println(password);
        setPassword(password);

        String facultyHeadRoleFromReq = setFacultyRole(role);

        //Add set of roles
        Role facultyHeadRole = roleRepository.findByAuthority(facultyHeadRoleFromReq).orElseThrow(() -> new ResourceNotFoundException("FACULTY HEAD role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(facultyHeadRole);

        ApplicationUser applicationUser = new ApplicationUser(username, passwordEncoder.encode(password), email, roles);
        return modelMapper.map(applicationUser, AppUserDto.class);
    }

    @Override
    public JwtAuthResponseDto login(LoginDto request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        Optional<ApplicationUser> userOptional = applicationUserRepository.findByUsername(request.getUsername());

        String role = null;
        Integer userId = null;

        if(userOptional.isPresent()) {
            ApplicationUser loggedInUser = userOptional.get();
            userId = loggedInUser.getId();
            Optional<Role > optionalRole = (Optional<Role>) loggedInUser.getAuthorities().stream().findFirst();
            if(optionalRole.isPresent()) {
                Role userRole = optionalRole.get();
                role = userRole.getAuthority();
            }
        }

        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setRole(role);
        jwtAuthResponseDto.setAccessToken(token);
        jwtAuthResponseDto.setUserId(userId);

        return jwtAuthResponseDto;
    }

    @Override
    public void setPassword(String password) {
        this.PASSWORD = password;
    }

    public String getPassword() {
        return this.PASSWORD;
    }

    /**
     * This private method handles the roles for faculty head
     * based on the role passed from the request
     * @param role
     * @return
     */
    private String setFacultyRole(String role) {
        String facultyHeadRole = null;
        if (StringUtils.hasText(role) && "Department Chairman".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_DEPARTMENT_CHAIRMAN;
        } else if(StringUtils.hasText(role) && "College Dean".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_COLLEGE_DEAN;
        } else if(StringUtils.hasText(role) && "School Director".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_SCHOOL_DIRECTOR;
        }else if (StringUtils.hasText(role) && "SG Adviser".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_SG_ADVISER;
        } else if (StringUtils.hasText(role) && "Campus Ministry".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_CAMPUS_MINISTRY;
        } else if (StringUtils.hasText(role) && "Guidance Office".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_GUIDANCE_OFFICE;
        } else if (StringUtils.hasText(role) && "Library In-Charge".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_LIBRARIAN;
        } else if (StringUtils.hasText(role) && "Dispensary In-Charge".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_DISPENSARY;
        } else if (StringUtils.hasText(role) && "Property Custodian".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_PROPERTY_CUSTODIAN;
        } else if (StringUtils.hasText(role) && "Prefect Of Discipline".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_PREFECT_DISCIPLINE;
        } else if (StringUtils.hasText(role) && "Registrar".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_REGISTRAR;
        } else if (StringUtils.hasText(role) && "Finance".equalsIgnoreCase(role)) {
            facultyHeadRole = ROLE_FINANCE;
        } else {
            facultyHeadRole = "ROLE_FACULTY";
        }

        return facultyHeadRole;
    }
}
