package com.example.fivemealsmobileproject.ui.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.ui.login.SessionManager;
import com.example.fivemealsmobileproject.ui.main.TableInfo;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerMethod;
    private ArrayList<PaymentMethod> paymentMethods;
    private PaymentAdapter adapter;
    private TextView totalSummary;
    private TextView taxSummary;
    private List<PaymentProduct> paymentProducts;
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        this.cacheViews();
        this.paymentMethods = PaymentMethodsDB.getPaymentMethods();

        SpinnerAdapter adapterSpinner = new SpinnerAdapter(this,paymentMethods);
        if(spinnerMethod != null){
            spinnerMethod.setAdapter(adapterSpinner);
            spinnerMethod.setOnItemSelectedListener(this);
        }

        this.paymentProducts = AppDataBase.getInstance(this).getOrderProductDAO().getAllPaymentProducts(
                SessionManager.getActiveSession(this),
                TableInfo.getRestaurant().getRestaurantID()
        );

        RecyclerView recyclerView = findViewById(R.id.recyclerViewActivityPayment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.adapter = new PaymentAdapter(paymentProducts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        this.calculateTotalPrice();
        Context context = this;
        ImageView imageViewGoBack = findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void calculateTotalPrice(){
        float total = 0;
        for (PaymentProduct paymentProduct : this.paymentProducts) {
            float unitPrice = paymentProduct.getUnitPrice();
            float quantityProduct = paymentProduct.getQuantity();
            float totalPriceProduct = unitPrice * quantityProduct;
            total = total + totalPriceProduct;
            this.totalSummary.setText(String.format("Total: %s€", total));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void cacheViews(){
        this.spinnerMethod = findViewById(R.id.spinnerActivityPaymentPaymentMethod);
        this.totalSummary = findViewById(R.id.textViewActivityPaymentTotal);
    }
}