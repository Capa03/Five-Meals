package com.example.fivemealsmobileproject.order;

import android.view.View;

public class ParentProduct {
    private long productID;
    private int state;

    public ParentProduct(long productID) {
        this.productID = productID;
        this.state = View.GONE;
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
}
