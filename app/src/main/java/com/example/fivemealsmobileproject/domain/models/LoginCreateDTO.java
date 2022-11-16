package com.example.fivemealsmobileproject.domain.models;

public class LoginCreateDTO {

    private String identifier;
    private String passwordHash;

    public LoginCreateDTO() {

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
