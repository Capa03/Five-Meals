package com.example.fivemealsmobileproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
@Entity
public class OrderProduct {

    public static int PROCESSING_STATE = 0;
    public static int PENDING_STATE = 1;
    public static int WAITING_APPROVAL_STATE = 2;

    @PrimaryKey(autoGenerate = true)
    private long orderProductID;
    private long productID;
    private int state;

    public OrderProduct(long productID,int state) {
        this.productID = productID;
        this.state = state;
    }

    public void setOrderProductID(long orderProductID) {
        this.orderProductID = orderProductID;
    }

    public long getOrderProductID() {
        return orderProductID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }


}
