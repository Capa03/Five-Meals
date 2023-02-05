package com.example.fivemealsmobileproject.ui.order;

import android.app.Activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.repository.order.OrderRepository;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;

import java.util.ArrayList;
import java.util.List;

public class ParentProductDB {

    private static ParentProductDB INSTANCE;
    private static OrderRepository orderRepository;

    private MutableLiveData<List<ParentOrderProduct>> parentOrderProductLiveData = new MutableLiveData<>();
    private OrderProductDAO orderProductDAO;

    private ParentProductDB(Activity activity){
        this.orderProductDAO = AppDataBase.getInstance(activity).getOrderProductDAO();
        this.orderProductDAO.getAllProductsNoDupes(orderRepository.getSavedOrderId()).
                observe((LifecycleOwner) activity, orderProducts -> {
                    List<ParentOrderProduct> results = new ArrayList<>();
                    for (OrderProduct queryOrderProduct: orderProducts) {
                        boolean exists = false;
                        if(parentOrderProductLiveData.getValue() != null)
                            for (ParentOrderProduct parentOrderProduct: parentOrderProductLiveData.getValue()) {
                                if(queryOrderProduct.getProductID() == parentOrderProduct.getProductID()){
                                    exists = true;
                                    results.add(parentOrderProduct);
                                    break;
                                }

                            }
                        if(!exists) {
                            ParentOrderProduct productToAdd = new ParentOrderProduct(
                                    queryOrderProduct.getProductID(),
                                    queryOrderProduct.getProductName(),
                                    queryOrderProduct.getProductPrice(),
                                    queryOrderProduct.getProductMinAverageTime(),
                                    queryOrderProduct.getProductMaxAverageTime(),
                                    orderProductDAO.getQuantityFromID(queryOrderProduct.getProductID()),
                                    orderProductDAO.getAllFromID(queryOrderProduct.getProductID()),
                                    queryOrderProduct.getImgLink()
                            );
                            results.add(productToAdd);
                        }
                    }
                    parentOrderProductLiveData.postValue(results);
                });

    }

    public LiveData<List<ParentOrderProduct>> getAllLiveData(){
        return parentOrderProductLiveData;
    }

    public static ParentProductDB getInstance(Activity activity){
        if(INSTANCE == null){
            orderRepository = new OrderRepository(activity);
            INSTANCE = new ParentProductDB(activity);
        }
        return INSTANCE;
    }

    public static void clearInstance(){
        INSTANCE = null;
    }

}
