package com.example.fivemealsmobileproject.ui.qrcode.viewmodels;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.repository.LocalizationRepository;

public class QrCodeActivityViewModel extends AndroidViewModel {

    private LocalizationRepository repository;


    public QrCodeActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.repository = new LocalizationRepository(activity);
    }

    public LiveData<Boolean> getTableFromId(int tableId){
        return repository.fetchTableById(tableId);
    }
}
