package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM Category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM Category WHERE RestaurantID = :restaurantID")
    List<Category> getAllCategoriesFromRestaurant(long restaurantID);

    @Query("SELECT categoryID FROM Category WHERE RestaurantID = :restaurantID AND categoryName = :categoryName")
    long getCategoryIDFromRestaurantIdAndCategoryName(long restaurantID, String categoryName);

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Insert
    void insertCategory(Category category);

}
