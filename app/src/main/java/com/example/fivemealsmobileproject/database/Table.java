package com.example.fivemealsmobileproject.database;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Table {

    @PrimaryKey(autoGenerate = true)
    private long tableID;
    private long restaurantID;

    public Table(long restaurantID) {
        this.restaurantID = restaurantID;
    }

    public long getTableID() {
        return tableID;
    }

    public void setTableID(long tableID) {
        this.tableID = tableID;
    }

    public long getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(long restaurantID) {
        this.restaurantID = restaurantID;
    }
}
