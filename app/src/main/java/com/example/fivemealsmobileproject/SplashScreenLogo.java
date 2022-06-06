package com.example.fivemealsmobileproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.example.fivemealsmobileproject.login.PreLoginActivity;
import com.example.fivemealsmobileproject.login.SessionManager;
import com.example.fivemealsmobileproject.qrcode.CodeActivity;


public class SplashScreenLogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Context context = this;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {

                if(SessionManager.sessionExists(context)){
                    CodeActivity.startActivity(context);
                }else {
                    PreLoginActivity.startActivity(context);
                }
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(r, 1000);
    }
}