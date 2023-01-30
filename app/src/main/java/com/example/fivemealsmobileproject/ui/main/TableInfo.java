package com.example.fivemealsmobileproject.ui.main;

import com.example.fivemealsmobileproject.datasource.room.Restaurant;
import com.example.fivemealsmobileproject.datasource.models.Table;

public class TableInfo {
    private static Table table;
    private static Restaurant restaurant;

    private TableInfo(){}


    public static Table getTable() {
        return table;
    }

    public static void setTable(Table table) {
        TableInfo.table = table;
    }

    public static Restaurant getRestaurant() {
        return restaurant;
    }

    public static void setRestaurant(Restaurant restaurant) {
        TableInfo.restaurant = restaurant;
    }
}
