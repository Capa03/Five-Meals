package com.example.fivemealsmobileproject.datasource.repository.login;

import android.content.Context;

import com.example.fivemealsmobileproject.datasource.remote.service.UserService;
import com.example.fivemealsmobileproject.domain.models.UserCreateDTO;
import com.example.fivemealsmobileproject.domain.models.UserShowDTO;
import com.example.fivemealsmobileproject.ui.login.SessionManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountRepository {
    private final String CAPA = "http://10.0.2.2:5011/";
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(CAPA)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private UserService userService;

    private Executor executor = Executors.newSingleThreadExecutor();
    private Context context;
    public CreateAccountRepository(Context context){
        this.userService = retrofit.create(UserService.class);
        this.context = context;
    }


    public boolean createAccount(UserCreateDTO user){
        this.userService.createUser(user).enqueue(new Callback<UserShowDTO>() {
            @Override
            public void onResponse(Call<UserShowDTO> call, Response<UserShowDTO> response) {
                if(response.isSuccessful()){
                    UserShowDTO user = response.body();
                    SessionManager.saveSession(context,user.getUsername(),true);
                }
            }

            @Override
            public void onFailure(Call<UserShowDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return false;
    }
}
