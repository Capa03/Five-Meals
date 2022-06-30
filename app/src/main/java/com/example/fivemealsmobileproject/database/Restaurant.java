package com.example.fivemealsmobileproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    private long restaurantID;
    private String restaurantName;

    public Restaurant(long restaurantID, String restaurantName) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
    }

    public long getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(long restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
