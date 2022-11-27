package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OrderProduct {

    public static int PROCESSING_STATE = 0;
    public static int PENDING_STATE = 1;
    public static int WAITING_APPROVAL_STATE = 2;
    public static int DELIVERED_STATE = 3;

    @PrimaryKey(autoGenerate = true)
    private long orderProductID;
    private String username;
    private long tableID;
    private int state;
    private long orderedTime;

    private long productID;
    private String productName;
    private Float productPrice;
    private float productMinAverageTime;
    private float productMaxAverageTime;
    private String imgLink;

    public OrderProduct(String username, long tableID, int state, long orderedTime, long productID, String productName, float productPrice, float productMinAverageTime, float productMaxAverageTime, String imgLink) {
        this.username = username;
        this.tableID = tableID;
        this.state = state;
        this.orderedTime = orderedTime;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productMinAverageTime = productMinAverageTime;
        this.productMaxAverageTime = productMaxAverageTime;
        this.imgLink = imgLink;
    }

    public void setOrderProductID(long orderProductID) {
        this.orderProductID = orderProductID;
    }

    public String getImgLink() {
        return imgLink;
    }

    public long getOrderProductID() {
        return orderProductID;
    }

    public String getUsername() {
        return username;
    }

    public long getTableID() {
        return tableID;
    }

    public int getState() {
        return state;
    }

    public long getOrderedTime() {
        return orderedTime;
    }

    public long getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public float getProductMinAverageTime() {
        return productMinAverageTime;
    }

    public float getProductMaxAverageTime() {
        return productMaxAverageTime;
    }
}
