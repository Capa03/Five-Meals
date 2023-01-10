package com.example.fivemealsmobileproject.datasource.remote.service;

import com.example.fivemealsmobileproject.datasource.room.User;
import com.example.fivemealsmobileproject.domain.models.LoginCreateDTO;
import com.example.fivemealsmobileproject.domain.models.UserCreateDTO;
import com.example.fivemealsmobileproject.domain.models.UserShowDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("User/{userId}")
    Call<User> getUser(@Path("userId") int id);

    @POST("User")
    Call<UserShowDTO> createUser(@Body UserCreateDTO newUser);

}
