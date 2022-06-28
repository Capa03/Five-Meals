package com.example.fivemealsmobileproject.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.main.MainActivity;

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

    @SuppressLint("SetTextI18n")
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

        this.paymentProducts = AppDataBase.getInstance(this).getOrderProductDAO().getAllPaymentProducts();

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
                MainActivity.startActivity(context,0);
            }
        });

    }

    private void calculateTotalPrice(){
        float total = 0;
        for(int i = 0; i< this.paymentProducts.size();i++){
            float unitPrice = this.paymentProducts.get(i).getUnitPrice();
            float quantityProduct = this.paymentProducts.get(i).getQuantity();
            float totalPriceProduct = unitPrice * quantityProduct;
            total = total + totalPriceProduct;
            this.totalSummary.setText("Total: " + total);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PaymentMethod paymentMethod = (PaymentMethod) parent.getSelectedItem();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void cacheViews(){
        this.spinnerMethod = findViewById(R.id.spinnerActivityPaymentPaymentMethod);
        this.totalSummary = findViewById(R.id.textViewActivityPaymentTotal);
        this.taxSummary = findViewById(R.id.textViewActivityPaymentTax);
    }
}