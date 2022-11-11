package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteProduct {
    @PrimaryKey
    private long productID;
    private String username;
    private long restaurantID;
    private String productName;
    private float productPrice;
    private String productImage;

    public FavoriteProduct(long productID, String username, long restaurantID, String productName, float productPrice, String productImage) {
        this.productID = productID;
        this.username = username;
        this.restaurantID = restaurantID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(long restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
