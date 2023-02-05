package com.example.fivemealsmobileproject.datasource.remote;

import com.example.fivemealsmobileproject.datasource.models.FavoriteToggleDTO;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavoriteService {

    @GET("Favorite")
    Call<List<FavoriteProduct>> getAllFavoritesFromEmail(@Query("userEmail") String userEmail, @Header("Authorization") String authHeader);

    @POST("Favorite")
    Call<Void> toggleFavoriteState(@Body FavoriteToggleDTO favoriteToggleDTO, @Header("Authorization") String authHeader);
}
