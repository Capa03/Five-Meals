package com.example.fivemealsmobileproject.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fivemealsmobileproject.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter {
    public SpinnerAdapter(@NonNull Context context, ArrayList<PaymentMethod> paymentMethods) {
        super(context, 0, paymentMethods);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_row, parent, false);
        }
        PaymentMethod paymentMethod = (PaymentMethod) getItem(position);
        ImageView spinnerImage = convertView.findViewById(R.id.imageViewSpinnerRow);
        TextView spinnerTitle = convertView.findViewById(R.id.textViewSpinnerRow);
        if (paymentMethod != null) {
            spinnerImage.setImageResource(paymentMethod.getSpinnerImage());
            spinnerTitle.setText(paymentMethod.getSpinnerTitle());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_row, parent, false);
        }
        PaymentMethod paymentMethod = (PaymentMethod) getItem(position);
        ImageView spinnerImage = convertView.findViewById(R.id.imageViewSpinnerRow);
        TextView spinnerTitle = convertView.findViewById(R.id.textViewSpinnerRow);
        if (paymentMethod != null) {
            spinnerImage.setImageResource(paymentMethod.getSpinnerImage());
            spinnerTitle.setText(paymentMethod.getSpinnerTitle());
        }
        return convertView;
    }
}