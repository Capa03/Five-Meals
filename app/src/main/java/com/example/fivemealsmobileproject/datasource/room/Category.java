package com.example.fivemealsmobileproject.datasource.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey (autoGenerate = false)
    private long categoryID;

    private long restaurantID;
    @NonNull
    private String categoryName;

    public Category(long categoryID, long restaurantID, @NonNull String categoryName) {
        this.categoryID = categoryID;
        this.restaurantID = restaurantID;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public long getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(long restaurantID) {
        this.restaurantID = restaurantID;
    }
}
