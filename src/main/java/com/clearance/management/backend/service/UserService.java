package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.AppUserDto;
import com.clearance.management.backend.request.UpdatePasswordRequest;
import com.clearance.management.backend.request.UserRequest;

public interface UserService {
    public Boolean verifyPassword(UserRequest request);
    public AppUserDto updatePassword(UpdatePasswordRequest request);
}
