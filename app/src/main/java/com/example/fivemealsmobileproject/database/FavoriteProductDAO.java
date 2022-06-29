package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteProductDAO {

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE productID = :productId")
    FavoriteProduct getFromId(long productId);

    @Query("SELECT * FROM FAVORITEPRODUCT")
    List<FavoriteProduct> getAllFavorite();
    @Insert
    void insertFavorite(FavoriteProduct favoriteProduct);

    @Delete
    void deleteFavorite(FavoriteProduct favoriteProduct);
}
