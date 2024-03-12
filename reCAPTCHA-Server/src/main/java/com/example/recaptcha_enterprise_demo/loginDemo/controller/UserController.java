package com.example.recaptcha_enterprise_demo.loginDemo.controller;

import com.example.recaptcha_enterprise_demo.loginDemo.domain.dto.LoginDto;
import com.example.recaptcha_enterprise_demo.util.RecaptchaUtil;
import com.example.recaptcha_enterprise_demo.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login_demo")
public class UserController {
    @Resource
    private RecaptchaUtil recaptchaUtil;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        if (recaptchaUtil.verify(loginDto.getRecaptchaToken(), "LOGIN")) {
            return Result.success("reCAPTCHA verified success");
        } else {
            return Result.error(RecaptchaUtil.getErrorMessage());
        }
    }
}
