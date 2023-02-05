package com.example.fivemealsmobileproject.datasource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteProductDAO {

    @Query("SELECT * FROM FAVORITEPRODUCT")
    LiveData<List<FavoriteProduct>> getAllFavorites();

    @Query("SELECT * FROM FAVORITEPRODUCT WHERE productID = :productId")
    LiveData<FavoriteProduct> getFavoriteLiveDataFromId(long productId);

    @Insert
    void insertFavorite(List<FavoriteProduct> favoriteProducts);

    @Query("DELETE FROM FavoriteProduct")
    void clearTable();
}
