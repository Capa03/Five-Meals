package com.example.fivemealsmobileproject.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;

import org.w3c.dom.Text;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private List<PaymentProduct> paymentProducts;

    public PaymentAdapter(List<PaymentProduct> paymentMethods){
        this.paymentProducts = paymentMethods;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_payment_recycler_view_resume_products,parent,false);

        return new PaymentViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
      PaymentProduct paymentProduct = this.paymentProducts.get(position);

      holder.setQuantity(paymentProduct.getQuantity());
      holder.setName(paymentProduct.getProductName());
      holder.setUnitPrice(paymentProduct.getUnitPrice());
      holder.setTotalPrice(paymentProduct.getUnitPrice() * paymentProduct.getQuantity());

    }

    @Override
    public int getItemCount() {
        return paymentProducts.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView quantity;
        private TextView name;
        private TextView priceUnit;
        private TextView priceTotal;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.name = itemView.findViewById(R.id.textViewPaymentActivityNameProduct);
            this.quantity = itemView.findViewById(R.id.textViewPaymentActivityQuantity);
            this.priceUnit = itemView.findViewById(R.id.textViewPaymentActivityUnitPrice);
            this.priceTotal = itemView.findViewById(R.id.textViewPaymentActivityTotalPrice);
        }

        public void setQuantity(int quantity){
            this.quantity.setText(String.format("%sx", quantity));
        }

        public void setName(String name){
            this.name.setText(name);
        }

        public void setUnitPrice(float price){
            this.priceUnit.setText((String.format("%s €", price)));
        }

        public void setTotalPrice(float price){
            this.priceTotal.setText((String.format("%s €", price)));
        }
    }
}
