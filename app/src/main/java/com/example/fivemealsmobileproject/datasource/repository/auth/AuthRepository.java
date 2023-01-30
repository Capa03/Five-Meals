package com.example.fivemealsmobileproject.datasource.repository.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.remote.AuthService;
import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenResponse;
import com.example.fivemealsmobileproject.datasource.models.auth.UserCreateDTO;
import com.example.fivemealsmobileproject.datasource.models.auth.UserShowDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthRepository {

    private final String CAPA = "http://10.0.2.2:5168/";
    private final String LUIS = "http://192.168.1.70:5168";

    private final SharedPreferences sharedPreferences;
    private final String KEY_EMAIL = "keyEmail";
    private final String KEY_PASS = "keyPassHash";
    private final String KEY_TOKEN = "keyToken";

    private MutableLiveData<String> tokenLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> successLiveData = new MutableLiveData<>();

    private final Context context;
    private final AuthService authService;


    public AuthRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LUIS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.authService = retrofit.create(AuthService.class);
        this.sharedPreferences = context.getSharedPreferences("sessionPreferences", Context.MODE_PRIVATE);
        this.context = context;
    }

    public void saveEmail(String email){
        this.sharedPreferences.edit().putString(KEY_EMAIL, email).apply();
    }

    public String getSavedEmail(){
        return this.sharedPreferences.getString(KEY_EMAIL, "");
    }

    public void savePasswordHash(String hash){
        this.sharedPreferences.edit().putString(KEY_PASS, hash).apply();
    }

    public String getSavedPasswordHash(){
        return this.sharedPreferences.getString(KEY_PASS, "");
    }

    public void saveToken(String token){
        this.sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public void clearToken(){
        this.sharedPreferences.edit().putString(KEY_TOKEN, "").apply();
    }

    public String getSavedToken(){
        return this.sharedPreferences.getString(KEY_TOKEN, "");
    }

    public LiveData<Boolean> createUser(UserCreateDTO userIn){

        this.authService.createUser(userIn).enqueue(new Callback<UserShowDTO>() {
            @Override
            public void onResponse(Call<UserShowDTO> call, Response<UserShowDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Account already exists", Toast.LENGTH_LONG).show();
                }else {
                    saveEmail(userIn.getEmail());
                    savePasswordHash(userIn.getPassword());
                    successLiveData.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<UserShowDTO> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(context, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
        return this.successLiveData;
    }

    public LiveData<String> fetchToken(GetTokenRequest getTokenRequest){
        if(getTokenRequest.getEmail().equals("") || getSavedPasswordHash().equals("")){
            return this.tokenLiveData;
        }

        this.authService.getToken(getTokenRequest).enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Wrong credentials", Toast.LENGTH_LONG).show();
                }else {
                    tokenLiveData.postValue(response.body().getToken());
                }
            }

            @Override
            public void onFailure(Call<GetTokenResponse> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(context, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
        return this.tokenLiveData;
    }
}
