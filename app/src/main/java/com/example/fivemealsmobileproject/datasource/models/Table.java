package com.example.fivemealsmobileproject.datasource.models;


public class Table {
    private final int id;
    private final int restaurantID;

    public Table(int id, int restaurantID) {
        this.id = id;
        this.restaurantID = restaurantID;
    }

    public int getId() {
        return id;
    }
    public int getRestaurantID() {
        return restaurantID;
    }

}
