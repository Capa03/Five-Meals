package com.example.fivemealsmobileproject.domain.models;

public class UserCreateDTO {
    private String username;
    private String email;
    private String password;

    public UserCreateDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
