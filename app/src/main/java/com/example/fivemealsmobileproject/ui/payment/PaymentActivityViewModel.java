package com.example.fivemealsmobileproject.ui.payment;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.repository.localization.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.order.OrderRepository;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;

import java.util.List;

public class PaymentActivityViewModel extends AndroidViewModel {

    private OrderRepository orderRepository;
    private LocalizationRepository localizationRepository;

    public PaymentActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.orderRepository = new OrderRepository(activity);
        this.localizationRepository = new LocalizationRepository(activity);
    }

    public LiveData<List<OrderProduct>> getOrderProducts(){
        return this.orderRepository.getOrderProductsLiveData();
    }

    public float calculateTotalPrice(List<OrderProduct> orderProducts){
        float total = 0;
        for (OrderProduct orderProduct : orderProducts) {
            total += orderProduct.getProductPrice();
        }
        return total;
    }

    public void payAll(List<OrderProduct> orderProductsToPay) {
        this.orderRepository.payAll(orderProductsToPay);
    }
}
