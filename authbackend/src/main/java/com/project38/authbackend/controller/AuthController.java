package com.project38.authbackend.controller;

import com.project38.authbackend.model.ApplicationUser;
import com.project38.authbackend.dto.LoginResponseDTO;
import com.project38.authbackend.dto.RegistrationDTO;
import com.project38.authbackend.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
    // In more complex setups RegistrationDTO and LoginResponseDTO would be used separately
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private final AuthenticationService authenticationService;
}
