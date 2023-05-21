package com.project38.authbackend.dto;

import com.project38.authbackend.model.ApplicationUser;

public class LoginResponseDTO {

    public LoginResponseDTO(ApplicationUser user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public ApplicationUser getUser() {
        return this.user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponseDTO() {}

    private ApplicationUser user;
    private String jwt;
}
