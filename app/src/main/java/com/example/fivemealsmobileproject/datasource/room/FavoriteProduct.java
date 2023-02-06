package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteProduct {
    @PrimaryKey
    private long productID;
    private String productName;
    private float productPrice;
    private String productImage;
    private long restaurantID;

    public FavoriteProduct(long productID, String productName, float productPrice, String productImage, long restaurantID) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.restaurantID = restaurantID;
    }

    public long getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public long getRestaurantID() {
        return restaurantID;
    }

}
