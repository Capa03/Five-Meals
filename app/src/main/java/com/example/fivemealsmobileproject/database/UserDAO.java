package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
