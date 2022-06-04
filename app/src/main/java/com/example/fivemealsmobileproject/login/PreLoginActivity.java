package com.example.fivemealsmobileproject.login;

import androidx.appcompat.app.AppCompatActivity;

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


    public void onHaveAccountClicked(View view) {
        LoginActivity.startActivity(this);
        finish();
    }

    public void onNewAccountClicked(View view) {
        CreateNewAccountActivity.startActivity(this);
        finish();
    }
}