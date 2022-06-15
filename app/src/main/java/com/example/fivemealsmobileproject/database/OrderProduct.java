package com.example.fivemealsmobileproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
@Entity
public class OrderProduct {
    @PrimaryKey
    private long productID;
    private int quantity;
    private boolean later;

    public OrderProduct(long productID, int quantity, boolean later) {
        this.productID = productID;
        this.quantity = quantity;
        this.later = later;
    }

    public boolean isLater() {
        return later;
    }

    public void setLater(boolean later) {
        this.later = later;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
