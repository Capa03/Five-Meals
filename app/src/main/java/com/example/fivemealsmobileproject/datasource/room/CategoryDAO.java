package com.example.fivemealsmobileproject.datasource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM Category WHERE RestaurantID = :restaurantID")
    LiveData<List<Category>> getAllCategoriesFromRestaurant(long restaurantID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Query("DELETE FROM Category")
    void clearCategories();
}
