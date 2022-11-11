package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDAO {

    @Query("SELECT * FROM Restaurant WHERE restaurantID = :restaurantID")
    Restaurant getRestaurantFromID(long restaurantID);

    @Query("SELECT * FROM Restaurant")
    List<Restaurant> getAllRestaurants();

    @Insert
    void insertRestaurant(Restaurant restaurant);
}
