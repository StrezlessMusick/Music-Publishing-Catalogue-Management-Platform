package com.project38.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
    @GetMapping("")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }

    @GetMapping("/secure")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public String secure() {
        return "Hello, Secured!";
    }
}

