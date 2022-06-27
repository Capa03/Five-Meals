package com.example.fivemealsmobileproject.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fivemealsmobileproject.R;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerMethod;
    private ArrayList<PaymentMethod> paymentMethods;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        this.spinnerMethod = findViewById(R.id.spinnerActivityPaymentPaymentMethod);
        this.paymentMethods = PaymentMethodsDB.getPaymentMethods();

        SpinnerAdapter adapter = new SpinnerAdapter(this,paymentMethods);
        if(spinnerMethod != null){
            spinnerMethod.setAdapter(adapter);
            spinnerMethod.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PaymentMethod paymentMethod = (PaymentMethod) parent.getSelectedItem();
        Toast.makeText(this,paymentMethod.getSpinnerTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}