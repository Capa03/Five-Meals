package com.example.fivemealsmobileproject.datasource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteProductDAO {

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE restaurantID = :restaurantId")
    LiveData<List<FavoriteProduct>> getAllFavoritesFromRestaurant(long restaurantId);

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE productID = :productId")
    LiveData<FavoriteProduct> getFavoriteLiveDataFromId(long productId);

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE productID = :productId")
    FavoriteProduct getFavoriteFromId(long productId);
    @Insert
    void insertFavorite(FavoriteProduct favoriteProduct);

    @Delete
    void deleteFavorite(FavoriteProduct favoriteProduct);
}
