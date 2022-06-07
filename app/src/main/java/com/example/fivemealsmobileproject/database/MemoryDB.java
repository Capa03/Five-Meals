package com.example.fivemealsmobileproject.database;

import android.content.Context;

public class MemoryDB {


    private MemoryDB(){
        // Private constructor
    }

    public static void populateRestaurantTables(Context context){

        RestaurantDAO restaurantDAO = AppDataBase.getInstance(context).getRestaurantDAO();
        TableDAO tableDAO = AppDataBase.getInstance(context).getTableDAO();
        if(restaurantDAO.getAllRestaurants().isEmpty()){
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantID(1);
            restaurant.setRestaurantName("Pulo do Lobo");
            restaurantDAO.insertRestaurant(restaurant);
            for(int i= 0; i < 10; i++){
                tableDAO.insertTable(new Table(restaurant.getRestaurantID()));
            }
        }
    }
}
