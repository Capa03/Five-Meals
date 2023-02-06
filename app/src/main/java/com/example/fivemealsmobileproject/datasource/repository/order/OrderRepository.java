package com.example.fivemealsmobileproject.datasource.repository.order;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.models.order.GetOrderRequest;
import com.example.fivemealsmobileproject.datasource.models.order.GetOrderResponse;
import com.example.fivemealsmobileproject.datasource.models.order.InsertOrderProductRequest;
import com.example.fivemealsmobileproject.datasource.models.order.OrderProductPatchDTO;
import com.example.fivemealsmobileproject.datasource.remote.OrderService;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderRepository {

    private final String CAPA = "http://10.0.2.2:5168/";
    private final String LUIS = "http://192.168.1.70:5168";
    private final String API = "https://fivemealsapi.azurewebsites.net";


    private final SharedPreferences sharedPreferences;
    private final String KEY_ORDER_ID = "keyOrderId";

    private final Activity activity;
    private final OrderService orderService;
    private final AuthRepository authRepository;
    private final OrderProductDAO orderproductDAO;


    public OrderRepository(Activity activity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.authRepository = new AuthRepository(activity);
        this.orderService = retrofit.create(OrderService.class);
        this.sharedPreferences = activity.getSharedPreferences("sessionPreferences", Context.MODE_PRIVATE);
        this.activity = activity;
        this.orderproductDAO = AppDataBase.getInstance(activity).getOrderProductDAO();
    }

    private void saveOrderId(int orderId){
        this.sharedPreferences.edit().putInt(KEY_ORDER_ID, orderId).apply();
    }

    public int getSavedOrderId(){
        return this.sharedPreferences.getInt(KEY_ORDER_ID, 0);
    }

    public void clearOrderId(){
        this.sharedPreferences.edit().putInt(KEY_ORDER_ID, 0).apply();
    }

    public void clearOrderProducts(){
        this.orderproductDAO.clearCurrentOrder();
    }


    public void fetchOrder(int tableId){
        this.orderService.getActiveOrder(new GetOrderRequest(tableId), this.authRepository.getSavedToken()).enqueue(new Callback<GetOrderResponse>() {
            @Override
            public void onResponse(Call<GetOrderResponse> call, Response<GetOrderResponse> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            fetchOrder(tableId);
                        }
                    });
                }else if(response.isSuccessful()){
                    saveOrderId(response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<GetOrderResponse> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void insertOrderProducts(List<InsertOrderProductRequest> productsToAdd) {
        this.orderService.insertOrderProducts(productsToAdd, this.authRepository.getSavedToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 401) {
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if (tokenSuccess) {
                            insertOrderProducts(productsToAdd);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void refreshOrderProducts(){
        this.orderService.getProductsFromOrder(getSavedOrderId(), this.authRepository.getSavedToken()).enqueue(new Callback<List<OrderProduct>>() {
            @Override
            public void onResponse(Call<List<OrderProduct>> call, Response<List<OrderProduct>> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            refreshOrderProducts();
                        }
                    });
                } else {
                    orderproductDAO.clearCurrentOrder();
                    orderproductDAO.insertOrderProducts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<OrderProduct>> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public LiveData<List<OrderProduct>> getOrderProductsNoDupesLiveData(){
        return this.orderproductDAO.getAllProductsNoDupes(getSavedOrderId());
    }

    public LiveData<List<OrderProduct>> getOrderProductsLiveData(){
        return this.orderproductDAO.getAllUnpaidDeliveredProducts(getSavedOrderId());
    }




    public void payAll(List<OrderProduct> orderProductsToPay) {
        List<OrderProductPatchDTO> requestList = new ArrayList<>();
        for (OrderProduct orderproduct: orderProductsToPay) {
            requestList.add(new OrderProductPatchDTO(
                    orderproduct.getOrderId(),
                    orderproduct.getOrderProductID(),
                    orderproduct.getStepsMade(),
                    true,
                    orderproduct.isDelivered()
            ));
        }

        this.orderService.updateOrderProducts(requestList, this.authRepository.getSavedToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                            authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            payAll(orderProductsToPay);
                        }
                    });
                }else {
                    refreshOrderProducts();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteOrderProduct(List<OrderProduct> orderProductList){
        List<Long> idList = new ArrayList<>();
        for (OrderProduct orderProduct: orderProductList) {
            idList.add(orderProduct.getOrderProductID());
        }

        this.orderService.deleteOrderProducts(idList, this.authRepository.getSavedToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            deleteOrderProduct(orderProductList);
                        }
                    });
                }else {
                    refreshOrderProducts();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
