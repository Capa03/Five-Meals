package com.example.fivemealsmobileproject.domain.models;

import java.util.Date;

public class UserShowDTO {
    private long id;
    private String username;
    private String email;
    private Date create;

    public UserShowDTO(long id, String username, String email,Date create) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.create = create;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreate() {
        return create;
    }
}
