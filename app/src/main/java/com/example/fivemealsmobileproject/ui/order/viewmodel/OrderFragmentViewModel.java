package com.example.fivemealsmobileproject.ui.order.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.repository.localization.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.order.OrderRepository;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;
import com.example.fivemealsmobileproject.ui.order.ParentOrderProduct;
import com.example.fivemealsmobileproject.ui.order.ParentProductDB;

import java.util.ArrayList;
import java.util.List;

public class OrderFragmentViewModel extends AndroidViewModel {

    private OrderRepository orderRepository;
    private LocalizationRepository localizationRepository;

    public OrderFragmentViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<ParentOrderProduct>> getParentOrderProducts(Activity activity){
        this.orderRepository = new OrderRepository(activity);
        this.localizationRepository = new LocalizationRepository(activity);
        return ParentProductDB.getInstance(activity).getAllLiveData();
    }

    public void fetchData(){
        this.orderRepository.refreshOrderProducts();
    }

    public void deleteOrderProduct(OrderProduct orderProduct) {
        List<OrderProduct> requestOrderProducts = new ArrayList<>();
        requestOrderProducts.add(orderProduct);
        this.orderRepository.deleteOrderProduct(requestOrderProducts);
    }
}
