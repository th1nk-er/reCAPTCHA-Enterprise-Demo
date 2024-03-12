package com.example.recaptcha_enterprise_demo.loginDemo.domain.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
    private String recaptchaToken;
    private boolean rememberMe;
}
