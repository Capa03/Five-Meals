package com.example.fivemealsmobileproject.ui.payment;

public class PaymentMethod {

    private String spinnerTitle;
    private int spinnerImage;

    public PaymentMethod(String spinnerTitle, int spinnerImage){
        this.spinnerTitle = spinnerTitle;
        this.spinnerImage = spinnerImage;
    }

    public String getSpinnerTitle() {
        return spinnerTitle;
    }

    public int getSpinnerImage() {
        return spinnerImage;
    }
}
