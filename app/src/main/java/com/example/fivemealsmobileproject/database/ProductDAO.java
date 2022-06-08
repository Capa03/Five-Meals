package com.example.fivemealsmobileproject.database;

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

    @Query("SELECT * FROM Product WHERE category = :category")
    List<Product> getAllFromCategory(String category);

    @Query("SELECT * FROM Product WHERE id = :id")
    Product getById(long id);

    @Delete
    void deleteProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Insert
    void insertProduct(Product product);

}
