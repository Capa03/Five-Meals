package com.example.fivemealsmobileproject.ui.main;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fivemealsmobileproject.datasource.repository.product.ProductsRepository;

public class MainActivityViewModel extends AndroidViewModel {

    ProductsRepository productsRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.productsRepository = new ProductsRepository(activity);
    }

    public void refreshDataSet(){
        this.productsRepository.refreshDataSet();
    }
}
