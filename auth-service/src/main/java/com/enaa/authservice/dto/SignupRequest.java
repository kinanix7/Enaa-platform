package com.enaa.authservice.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String role;
}