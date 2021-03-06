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
    public static int DELIVERED_STATE = 3;

    @PrimaryKey(autoGenerate = true)
    private long orderProductID;
    private long productID;
    private int state;
    private long orderedTime;

    public OrderProduct(long productID,int state, long orderedTime) {
        this.productID = productID;
        this.state = state;
        this.orderedTime = orderedTime;
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

    public long getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(long orderedTime) {
        this.orderedTime = orderedTime;
    }
}
