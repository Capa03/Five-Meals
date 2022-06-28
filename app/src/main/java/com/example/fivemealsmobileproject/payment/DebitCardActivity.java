package com.example.fivemealsmobileproject.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fivemealsmobileproject.R;

public class DebitCardActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DebitCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_card);
        Context context = this;
        ImageView imageViewGoBack = findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentActivity.startActivity(context);
            }
        });
    }

    public void onConfirm(View view) {
        PaymentActivity.startActivity(this);
    }
}