package com.clearance.management.backend.dto;

import com.clearance.management.backend.entity.Role;

import java.util.Set;

public class AppUserDto {
    private String username;
    private String password;
    private String email;

    private Set<Role> authorities;

    public AppUserDto() {
    }

    public AppUserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AppUserDto(String username, String password, String email, Set<Role> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
