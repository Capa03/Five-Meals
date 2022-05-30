package com.example.fivemealsmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.fivemealsmobileproject.login.PreLoginActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        SystemClock.sleep(2000);
        Intent intent = new Intent(this, PreLoginActivity.class);
        startActivity(intent);
    }










}