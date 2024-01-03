package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.request.UpdatePasswordRequest;
import com.clearance.management.backend.request.UserRequest;
import com.clearance.management.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

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
}
