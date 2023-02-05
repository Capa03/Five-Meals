package com.example.fivemealsmobileproject.ui.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerMethod;
    private TextView totalSummary;
    private PaymentActivityViewModel viewModel;
    private List<OrderProduct> orderProductsToPay;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        this.cacheViews();
        ArrayList<PaymentMethod> paymentMethods = PaymentMethodsDB.getPaymentMethods();

        this.viewModel = new ViewModelProvider(this).get(PaymentActivityViewModel.class);
        viewModel.initializeRepository(this);

        SpinnerAdapter adapterSpinner = new SpinnerAdapter(this, paymentMethods);
        if(spinnerMethod != null){
            spinnerMethod.setAdapter(adapterSpinner);
            spinnerMethod.setOnItemSelectedListener(this);
        }


        // TODO Pay all and update to the api
        RecyclerView recyclerView = findViewById(R.id.recyclerViewActivityPayment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        PaymentAdapter adapter = new PaymentAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getOrderProducts().observe(this, orderProducts -> {
            orderProductsToPay = orderProducts;
            adapter.updateDate(orderProducts);
            totalSummary.setText(String.format("Total: %s€", viewModel.calculateTotalPrice(orderProducts)));
        });

        ImageView imageViewGoBack = findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(imageViewGoBackView -> {
            finish();
        });

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

    public void onPayAllClick(View view) {
        this.viewModel.payAll(orderProductsToPay);
        finish();
    }
}