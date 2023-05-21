package com.project38.authbackend.dto;

public class RegistrationDTO {

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

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}
