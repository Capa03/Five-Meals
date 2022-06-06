package com.example.fivemealsmobileproject.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fivemealsmobileproject.R;

public class CodeActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Acabar o design
        setContentView(R.layout.activity_code_input);
        Toast.makeText(this, "QrCode", Toast.LENGTH_SHORT).show();
    }

    public void onQrCodeScanClick(View view) {
        QrCodeActivity.startActivity(this);
    }
}