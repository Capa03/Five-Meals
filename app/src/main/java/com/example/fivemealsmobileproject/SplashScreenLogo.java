package com.example.fivemealsmobileproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.Category;
import com.example.fivemealsmobileproject.database.SingleUseDataBase;
import com.example.fivemealsmobileproject.database.Table;
import com.example.fivemealsmobileproject.login.PreLoginActivity;
import com.example.fivemealsmobileproject.login.SessionManager;
import com.example.fivemealsmobileproject.qrcode.CodeActivity;

import java.util.List;


public class SplashScreenLogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Context context = this;


        SingleUseDataBase.populateRestaurantTables(this);
        SingleUseDataBase.populateCategoryTable(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Runnable r = () -> {

            if(SessionManager.sessionExists(context)){
                CodeActivity.startActivity(context);
            }else {
                PreLoginActivity.startActivity(context);
            }
            finish();
        };

        Handler handler = new Handler();
        handler.postDelayed(r, 1000);
    }
}