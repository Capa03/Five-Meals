package com.example.fivemealsmobileproject.datasource.remote;

import com.example.fivemealsmobileproject.datasource.models.Table;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface LocalizationService {

    @GET("Table/{tableId}")
    Call<Table> getTableById(@Path("tableId") int tableId, @Header("Authorization") String authHeader);

    @GET("Restaurant/{restaurantId}")
    Call<Table> getRestaurantById(@Path("restaurantId") int restaurantId, @Header("Authorization") String authHeader);
}
