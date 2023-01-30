package com.example.fivemealsmobileproject.ui.login.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;
import com.example.fivemealsmobileproject.datasource.models.auth.UserCreateDTO;

public class ViewModelCreateAccount extends AndroidViewModel {

    private final AuthRepository repository;

    public ViewModelCreateAccount(@NonNull Application application) {
        super(application);
        this.repository = new AuthRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> createUser(UserCreateDTO user) {
        return this.repository.createUser(user);
    }

    public LiveData<Boolean> getToken() {
        GetTokenRequest getTokenRequest = new GetTokenRequest(
                this.repository.getSavedEmail(),
                this.repository.getSavedPasswordHash());
        return this.repository.fetchToken(getTokenRequest);
    }
}
