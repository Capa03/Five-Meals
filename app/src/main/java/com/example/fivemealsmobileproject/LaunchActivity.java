package com.example.fivemealsmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO-

    /*
    public static void startActivity(Context context){
        Intent intent = new Intent(context, LaunchActivity.class);
        context.startActivity(intent);
    }
    */

}