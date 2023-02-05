package com.example.fivemealsmobileproject.datasource.repository.favorite;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.models.FavoriteToggleDTO;
import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.remote.FavoriteService;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProductDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteRepository {

    private final String CAPA = "http://10.0.2.2:5168/";
    private final String LUIS = "http://192.168.1.70:5168";

    private final Activity activity;
    private final FavoriteService favoriteService;
    private final AuthRepository authRepository;
    private final FavoriteProductDAO favoriteProductDAO;


    public FavoriteRepository(Activity activity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LUIS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.authRepository = new AuthRepository(activity);
        this.favoriteService = retrofit.create(FavoriteService.class);
        this.activity = activity;
        this.favoriteProductDAO = AppDataBase.getInstance(activity).getFavoriteProductDAO();
    }

    public void clearFavorites(){
        this.favoriteProductDAO.clearTable();
    }

    public void refreshFavorites(){
        this.favoriteService.getAllFavoritesFromEmail(authRepository.getSavedEmail(), authRepository.getSavedToken()).enqueue(new Callback<List<FavoriteProduct>>() {
            @Override
            public void onResponse(Call<List<FavoriteProduct>> call, Response<List<FavoriteProduct>> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            refreshFavorites();
                        }
                    });
                }else if(response.isSuccessful()){
                    favoriteProductDAO.clearTable();
                    favoriteProductDAO.insertFavorite(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<FavoriteProduct>> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void toggleFavorite(long productId){
        this.favoriteService.toggleFavoriteState(new FavoriteToggleDTO(productId, authRepository.getSavedEmail()), authRepository.getSavedToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            toggleFavorite(productId);
                        }
                    });
                }else if(response.isSuccessful()){
                    refreshFavorites();
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

    public LiveData<List<FavoriteProduct>> getFavoritesLiveData(){
        return this.favoriteProductDAO.getAllFavorites();
    }

    public LiveData<FavoriteProduct> getFavoriteLiveDataById(long productId){
        return this.favoriteProductDAO.getFavoriteLiveDataFromId(productId);
    }
}
