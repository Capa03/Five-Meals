package com.example.fivemealsmobileproject.ui.order;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.room.OrderProduct;

import java.util.List;

public class ParentOrderProduct {
    private long productID;
    private String productName;
    private float productPrice;
    private float productMinTime;
    private float productMaxTime;
    private LiveData<Integer> quantity;
    private LiveData<List<OrderProduct>> orderProductsLiveData;
    private int state;
    private String imgLink;


    public ParentOrderProduct(long productID, String productName, float productPrice, float productMinTime, float productMaxTime, LiveData<Integer> quantity, LiveData<List<OrderProduct>> orderProductsLiveData, String imgLink) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productMinTime = productMinTime;
        this.productMaxTime = productMaxTime;
        this.quantity = quantity;
        this.orderProductsLiveData = orderProductsLiveData;
        this.state = View.GONE;
        this.imgLink = imgLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public long getProductID() {
        return productID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public float getProductMinTime() {
        return productMinTime;
    }

    public float getProductMaxTime() {
        return productMaxTime;
    }

    public LiveData<Integer> getQuantity() {
        return quantity;
    }

    public LiveData<List<OrderProduct>> getOrderProductsLiveData() {
        return orderProductsLiveData;
    }
}
