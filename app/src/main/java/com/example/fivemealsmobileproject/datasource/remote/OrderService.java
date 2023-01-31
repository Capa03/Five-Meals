package com.example.fivemealsmobileproject.datasource.remote;

import com.example.fivemealsmobileproject.datasource.models.order.GetOrderRequest;
import com.example.fivemealsmobileproject.datasource.models.order.GetOrderResponse;
import com.example.fivemealsmobileproject.datasource.models.order.InsertOrderProductRequest;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @POST("Order")
    Call<GetOrderResponse> getActiveOrder(@Body GetOrderRequest getOrderRequest, @Header("Authorization") String authHeader);

    @POST("OrderProduct")
    Call<Void> insertOrderProducts(@Body List<InsertOrderProductRequest> orderProducts, @Header("Authorization") String authHeader);

    @GET("OrderProduct")
    Call<List<OrderProduct>> getProductsFromOrder(@Query("orderId") int orderId, @Header("Authorization") String authHeader);
}
