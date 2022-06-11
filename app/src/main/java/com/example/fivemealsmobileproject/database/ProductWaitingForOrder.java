package com.example.fivemealsmobileproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
@Entity
public class ProductWaitingForOrder {
    @PrimaryKey
    private long productID;
    private int quantity;

    public ProductWaitingForOrder(long productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
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
