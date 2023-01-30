package com.example.fivemealsmobileproject.datasource.remote;

import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenResponse;
import com.example.fivemealsmobileproject.datasource.models.auth.UserCreateDTO;
import com.example.fivemealsmobileproject.datasource.models.auth.UserShowDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("User")
    Call<UserShowDTO> createUser(@Body UserCreateDTO userCreateDTO);

    @POST("User/Token")
    Call<GetTokenResponse> getToken(@Body GetTokenRequest getTokenRequest);

}
