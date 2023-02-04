package com.example.fivemealsmobileproject.ui.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private List<OrderProduct> orderProducts = new ArrayList<>();

    public PaymentAdapter(){
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_payment_recycler_view_resume_products,parent,false);

        return new PaymentViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
      OrderProduct orderProduct = this.orderProducts.get(position);
      holder.setName(orderProduct.getProductName());
      holder.setUnitPrice(orderProduct.getProductPrice());
    }

    public void updateDate(List<OrderProduct> orderProducts){
        this.orderProducts = orderProducts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.orderProducts.size();
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
            this.priceUnit = itemView.findViewById(R.id.textViewPaymentActivityUnitPrice);
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
