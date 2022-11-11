package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteProductDAO {

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE productID = :productId AND username = :username AND restaurantID = :restaurantId")
    FavoriteProduct getFromId(long productId, String username, long restaurantId);

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE username = :username AND restaurantID = :restaurantId")
    List<FavoriteProduct> getAllFavorite(String username, long restaurantId);
    @Insert
    void insertFavorite(FavoriteProduct favoriteProduct);

    @Delete
    void deleteFavorite(FavoriteProduct favoriteProduct);
}
