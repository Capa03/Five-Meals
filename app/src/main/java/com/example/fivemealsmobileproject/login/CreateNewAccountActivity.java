package com.example.fivemealsmobileproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fivemealsmobileproject.R;

public class CreateNewAccountActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,CreateNewAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

    }

    public void onHaveAccountClicked(View view) {
        LoginActivity.startActivity(this);
        finish();
    }
}