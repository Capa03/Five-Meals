package com.example.fivemealsmobileproject.ui.order;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;
import com.example.fivemealsmobileproject.ui.login.SessionManager;
import com.example.fivemealsmobileproject.ui.main.TableInfo;

import java.util.ArrayList;
import java.util.List;

public class ParentProductDB {

    private static ParentProductDB INSTANCE;
    private MutableLiveData<List<ParentOrderProduct>> parentOrderProductLiveData = new MutableLiveData<>();
    private OrderProductDAO orderProductDAO;

    private ParentProductDB(Context context, LifecycleOwner observerOwner){
        this.orderProductDAO = AppDataBase.getInstance(context).getOrderProductDAO();
        this.orderProductDAO.getAllProductsNoDupes(TableInfo.getTable().getTableID()).
                observe(observerOwner, orderProducts -> {
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

    public static ParentProductDB getInstance(Context context, LifecycleOwner observerOwner){
        if(INSTANCE == null){
            INSTANCE = new ParentProductDB(context, observerOwner);
        }
        return INSTANCE;
    }

    public static void clearInstance(){
        INSTANCE = null;
    }

}
