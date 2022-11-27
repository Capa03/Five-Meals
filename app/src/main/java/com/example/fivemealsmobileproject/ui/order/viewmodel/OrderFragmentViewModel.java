package com.example.fivemealsmobileproject.ui.order.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;
import com.example.fivemealsmobileproject.ui.order.ParentOrderProduct;
import com.example.fivemealsmobileproject.ui.order.ParentProductDB;

import java.util.List;

public class OrderFragmentViewModel extends AndroidViewModel {
    private OrderProductDAO orderProductDAO;
    private Context context;

    public OrderFragmentViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.orderProductDAO = AppDataBase.getInstance(application).getOrderProductDAO();
    }

    public LiveData<List<ParentOrderProduct>> getParentOrderProducts(LifecycleOwner observerOwner){
        return ParentProductDB.getInstance(context, observerOwner).getAllLiveData();
    }
}
