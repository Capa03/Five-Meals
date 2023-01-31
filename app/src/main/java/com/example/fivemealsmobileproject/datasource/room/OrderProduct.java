package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OrderProduct {

    @PrimaryKey(autoGenerate = false)
    private final long orderProductID;
    private final long orderId;
    private final String userEmail;
    private final String orderedTime;

    private final long productID;
    private final String productName;
    private final Float productPrice;
    private final float productMinAverageTime;
    private final float productMaxAverageTime;
    private final String imgLink;

    private final int stepsMade;
    private final int maxSteps;
    private final boolean paid;

    public OrderProduct(long orderProductID, long orderId, String userEmail, String orderedTime, long productID, String productName, Float productPrice, float productMinAverageTime, float productMaxAverageTime, String imgLink, int stepsMade, int maxSteps, boolean paid) {
        this.orderProductID = orderProductID;
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.orderedTime = orderedTime;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productMinAverageTime = productMinAverageTime;
        this.productMaxAverageTime = productMaxAverageTime;
        this.imgLink = imgLink;
        this.stepsMade = stepsMade;
        this.maxSteps = maxSteps;
        this.paid = paid;
    }

    public long getOrderProductID() {
        return orderProductID;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getOrderedTime() {
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

    public String getImgLink() {
        return imgLink;
    }

    public int getStepsMade() {
        return stepsMade;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public boolean isPaid() {
        return paid;
    }
}
