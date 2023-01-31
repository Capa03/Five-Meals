package com.example.fivemealsmobileproject.ui.main;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fivemealsmobileproject.datasource.repository.localization.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.order.OrderRepository;
import com.example.fivemealsmobileproject.datasource.repository.product.ProductsRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private ProductsRepository productsRepository;
    private OrderRepository orderRepository;
    private LocalizationRepository localizationRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.productsRepository = new ProductsRepository(activity);
        this.orderRepository = new OrderRepository(activity);
        this.localizationRepository = new LocalizationRepository(activity);
    }

    public void refreshDataSet(){
        this.productsRepository.refreshDataSet();
        this.orderRepository.fetchOrder(this.localizationRepository.getSavedTableId());
    }
}
