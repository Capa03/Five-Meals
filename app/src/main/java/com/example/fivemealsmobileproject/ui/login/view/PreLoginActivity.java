package com.example.fivemealsmobileproject.ui.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fivemealsmobileproject.R;

public class PreLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PreLoginActivity.class);
        context.startActivity(intent);
    }


    public void onHaveAccountClicked(View view) {
        LoginActivity.startActivity(this);
        finish();
    }

    public void onNewAccountClicked(View view) {
        CreateNewAccountActivity.startActivity(this);
        finish();
    }
}