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
public interface ProductDAO {


    @Query("SELECT * FROM Product WHERE categoryName = :category AND restaurantId = :restaurantId")
    LiveData<List<Product>> getAllFromCategoryAndRestaurant(String category, long restaurantId);

    @Query("SELECT * FROM Product WHERE id = :id")
    LiveData<Product> getLiveDataById(long id);

    @Query("SELECT * FROM Product WHERE id = :id")
    Product getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<Product> products);

    @Query("DELETE FROM Product")
    void clearProducts();
}
