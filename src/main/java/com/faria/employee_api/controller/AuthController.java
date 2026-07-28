package com.faria.employee_api.controller;

import com.faria.employee_api.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public String getToken(){
        return jwtService.generateToken();
    }
}