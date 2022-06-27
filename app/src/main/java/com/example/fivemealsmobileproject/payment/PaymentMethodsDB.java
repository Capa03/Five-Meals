package com.example.fivemealsmobileproject.payment;

import com.example.fivemealsmobileproject.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsDB {

    private static ArrayList<PaymentMethod> paymentMethod = new ArrayList<>();

    private PaymentMethodsDB(){}

    public static ArrayList<PaymentMethod> getPaymentMethods(){
        paymentMethod = new ArrayList<>();
        paymentMethod.add(new PaymentMethod("PayPal",R.drawable.ic_baseline_account_circle_24));
        paymentMethod.add(new PaymentMethod("Cash",R.drawable.ic_baseline_account_circle_24));
        paymentMethod.add(new PaymentMethod("Debit Card",R.drawable.ic_baseline_account_circle_24));
        return paymentMethod;
    }
}
