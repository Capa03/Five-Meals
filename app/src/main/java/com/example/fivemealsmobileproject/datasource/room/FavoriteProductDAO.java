package com.example.fivemealsmobileproject.datasource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteProductDAO {

    @Query("SELECT * FROM FavoriteProduct WHERE FavoriteProduct.restaurantID == :restaurantId")
    LiveData<List<FavoriteProduct>> getAllFavoritesFromRestaurant(int restaurantId);

    @Query("SELECT * FROM FavoriteProduct WHERE productID = :productId")
    LiveData<FavoriteProduct> getFavoriteLiveDataFromId(long productId);

    @Insert
    void insertFavorite(List<FavoriteProduct> favoriteProducts);

    @Query("DELETE FROM FavoriteProduct")
    void clearTable();
}
