package com.example.fivemealsmobileproject.datasource.models.auth;

public class UserShowDTO {
    private final String username;
    private final String email;

    public UserShowDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
