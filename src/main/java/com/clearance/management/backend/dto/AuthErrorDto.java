package com.clearance.management.backend.dto;

public class AuthErrorDto {
    private String message;

    public AuthErrorDto() {
    }

    public AuthErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
