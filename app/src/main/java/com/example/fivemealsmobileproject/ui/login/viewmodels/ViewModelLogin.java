package com.example.fivemealsmobileproject.ui.login.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;

public class ViewModelLogin extends AndroidViewModel {

    private final AuthRepository repository;
    public ViewModelLogin(@NonNull Application application) {
        super(application);
        this.repository = new AuthRepository(application.getApplicationContext());
    }

    public LiveData<String> getToken(GetTokenRequest getTokenRequest) {

        return this.repository.fetchToken(getTokenRequest);
    }
}
