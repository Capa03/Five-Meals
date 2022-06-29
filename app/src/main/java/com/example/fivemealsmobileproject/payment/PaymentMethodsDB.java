package com.example.fivemealsmobileproject.payment;

import com.example.fivemealsmobileproject.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsDB {

    private static ArrayList<PaymentMethod> paymentMethod = new ArrayList<>();

    private PaymentMethodsDB(){}

    public static ArrayList<PaymentMethod> getPaymentMethods(){
        if(paymentMethod.isEmpty()) {
            paymentMethod.add(new PaymentMethod("MBWay", R.drawable.ic_mbway_seeklogo_com));
            paymentMethod.add(new PaymentMethod("Cash", R.drawable.ic_cash_svgrepo_com));
            paymentMethod.add(new PaymentMethod("Debit Card", R.drawable.ic_credit_card_svgrepo_com));
        }
        return paymentMethod;
    }
}
