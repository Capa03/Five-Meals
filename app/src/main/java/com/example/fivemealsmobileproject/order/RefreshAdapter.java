package com.example.fivemealsmobileproject.order;

import java.util.TimerTask;

public class RefreshAdapter extends TimerTask {
    private CurrentOrderAdapter orderedProductsAdapter;


    public RefreshAdapter(CurrentOrderAdapter orderedProductsAdapter){
        this.orderedProductsAdapter = orderedProductsAdapter;
    }

    @Override
    public void run() {
        this.orderedProductsAdapter.notifyDataSetChanged();
    }
}
