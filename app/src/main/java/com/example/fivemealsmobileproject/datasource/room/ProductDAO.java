package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Product ")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE category = :category AND restaurantId = :restaurantId")
    List<Product> getAllFromCategoryAndRestaurant(String category, long restaurantId);

    @Query("SELECT * FROM Product WHERE id = :id")
    Product getById(long id);

    @Delete
    void deleteProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Insert
    void insertProduct(Product product);

}
