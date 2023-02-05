package com.example.fivemealsmobileproject.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;

public class SplashScreenLogoViewModel extends AndroidViewModel {
    private AuthRepository authRepository;

    public SplashScreenLogoViewModel(@NonNull Application application) {
        super(application);
        this.authRepository = new AuthRepository(application.getApplicationContext());
    }

    public String getSavedEmail(){
        return authRepository.getSavedEmail();
    }
}
