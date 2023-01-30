package com.example.fivemealsmobileproject.datasource.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Table {
    private final int tableID;
    private final int restaurantID;

    public Table(int tableID, int restaurantID) {
        this.tableID = tableID;
        this.restaurantID = restaurantID;
    }

    public int getTableID() {
        return tableID;
    }
    public int getRestaurantID() {
        return restaurantID;
    }

}
