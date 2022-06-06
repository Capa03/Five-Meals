package com.example.fivemealsmobileproject.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.login.CreateNewAccountActivity;

public class QRCodeActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, QRCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }
}