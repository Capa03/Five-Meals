package com.example.fivemealsmobileproject.domain.models;

public class UserShowDTO {
    private long id;
    private String username;
    private String email;


    public UserShowDTO(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

    }
}
