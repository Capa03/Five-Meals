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

    // TODO getCategoryIDFromRestaurantIdAndCategoryName

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Insert
    void insertCategory(Category category);

}
