package com.example.fivemealsmobileproject.datasource.models.auth;

public class GetTokenRequest {
    private final String email;
    private final String password;

    public GetTokenRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
