package com.example.fivemealsmobileproject.ui.login.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fivemealsmobileproject.datasource.repository.login.CreateAccountRepository;
import com.example.fivemealsmobileproject.domain.models.UserCreateDTO;

public class ViewModelCreateAccount extends AndroidViewModel {
    private CreateAccountRepository repository;

    public ViewModelCreateAccount(@NonNull Application application) {
        super(application);
        this.repository = new CreateAccountRepository(application.getApplicationContext());
    }

    public boolean createUser(UserCreateDTO user) {
        return this.repository.createAccount(user);
    }

}
