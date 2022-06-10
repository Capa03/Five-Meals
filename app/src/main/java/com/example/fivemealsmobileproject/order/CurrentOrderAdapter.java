package com.example.fivemealsmobileproject.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.CurrentOrder;
import com.example.fivemealsmobileproject.database.Product;


import java.util.List;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderViewHolder> {

    private List<Product> products;

    public CurrentOrderAdapter(List<Product> product){
        this.products = product;
    }

    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_current_order,parent,false);
        return new CurrentOrderViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {
        Product currentOrder = CurrentOrder.getCurrentOrder().get(position);

        holder.setName(currentOrder.getName());
        holder.setTime(currentOrder.getAverageTime());
        holder.setPrice(currentOrder.getPrice());
        holder.setImage(currentOrder.getImgLink());

    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    public void updateData(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public class CurrentOrderViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private ImageView currentOrderImage;
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewPrice;
        private View itemView;

        public CurrentOrderViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            this.currentOrderImage = itemView.findViewById(R.id.imageViewCurrentOrder);
            this.textViewName = itemView.findViewById(R.id.textViewCurrentOrderName);
            this.textViewTime = itemView.findViewById(R.id.textViewCurrentOrderAverageTime);
            this.textViewPrice = itemView.findViewById(R.id.textViewCurrentOrderPrice);

            this.currentOrderImage.setClipToOutline(true);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(Float price) {
            this.textViewPrice.setText((String.valueOf(price) + " €"));
        }

        public void setTime(String time) {
            this.textViewTime.setText(time + " min");
        }

        public void setImageSrc(String link) {
            // TODO Glide implementation
        }

        public void setImage(String imageID) {
            // https://drive.google.com/uc?id=
            String link = "https://docs.google.com/uc?id=" + imageID;
            Glide.with(this.context).load(link).into(this.currentOrderImage);
        }
    }
}

