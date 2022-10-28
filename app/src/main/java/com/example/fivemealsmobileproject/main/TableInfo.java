package com.example.fivemealsmobileproject.main;

import com.example.fivemealsmobileproject.database.Restaurant;
import com.example.fivemealsmobileproject.database.Table;

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
