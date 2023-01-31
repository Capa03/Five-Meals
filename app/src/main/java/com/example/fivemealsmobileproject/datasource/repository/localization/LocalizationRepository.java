package com.example.fivemealsmobileproject.datasource.repository.localization;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.remote.LocalizationService;
import com.example.fivemealsmobileproject.datasource.models.Table;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocalizationRepository {
    private final String CAPA = "http://10.0.2.2:5168/";
    private final String LUIS = "http://192.168.1.70:5168";

    private final SharedPreferences sharedPreferences;
    private final String KEY_TABLE_ID = "keyTableId";
    private final String KEY_RESTAURANT_ID = "keyRestaurantId";

    private MutableLiveData<Boolean> successLiveData = new MutableLiveData<>();
    private final Activity activity;
    private final LocalizationService localizationService;
    private final AuthRepository authRepository;

    public LocalizationRepository(Activity activity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LUIS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.authRepository = new AuthRepository(activity);
        this.localizationService = retrofit.create(LocalizationService.class);
        this.sharedPreferences = activity.getSharedPreferences("sessionPreferences", Context.MODE_PRIVATE);
        this.activity = activity;
    }

    private void saveTableId(int tableId){
        this.sharedPreferences.edit().putInt(KEY_TABLE_ID, tableId).apply();
    }

    private void saveRestaurantId(int restaurantId){
        this.sharedPreferences.edit().putInt(KEY_RESTAURANT_ID, restaurantId).apply();
    }

    public int getSavedTableId(){
        return this.sharedPreferences.getInt(KEY_TABLE_ID, 0);
    }

    public int getSavedRestaurantId(){
        return this.sharedPreferences.getInt(KEY_RESTAURANT_ID, 0);
    }

    public LiveData<Boolean> fetchTableById(int tableId){
        this.localizationService.getTableById(tableId, authRepository.getSavedToken()).enqueue(new Callback<Table>() {
            @Override
            public void onResponse(Call<Table> call, Response<Table> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            fetchTableById(tableId);
                        }
                    });
                }else if(response.code() == 204){
                    //TODO Make strings
                    Toast.makeText(activity, "Table does not exist", Toast.LENGTH_SHORT).show();
                }else if(response.isSuccessful()){
                    saveTableId(response.body().getId());
                    saveRestaurantId(response.body().getRestaurantID());
                    successLiveData.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Table> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
        return this.successLiveData;
    }
}
