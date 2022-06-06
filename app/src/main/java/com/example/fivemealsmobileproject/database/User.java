package com.example.fivemealsmobileproject.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    private String email;
    private int password;

    public User(String username,String email,int password){
        this.username = username;
        this.email = email;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
