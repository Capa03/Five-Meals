package com.example.fivemealsmobileproject.datasource.remote;

import com.example.fivemealsmobileproject.datasource.models.CategoryWithProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ProductsService {

    @GET("CategoryWithProducts/{restaurantId}")
    Call<List<CategoryWithProducts>> getCategoriesWithProductsByRestaurantId(@Path("restaurantId") int restaurantId, @Header("Authorization") String authHeader);
}
