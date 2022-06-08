package com.example.fivemealsmobileproject.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    List<Product> products;


    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_simple, parent, false);
        return new ProductListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Product product = this.products.get(position);

        holder.setName(product.getName());
        holder.setTime(String.valueOf(product.getAverageTime()));
        holder.setPrice(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    public void updateData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewPrice;


        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productImage = itemView.findViewById(R.id.imageViewSimpleProduct);
            this.textViewName = itemView.findViewById(R.id.textViewSimpleProductName);
            this.textViewTime = itemView.findViewById(R.id.textViewSimpleAverageTime);
            this.textViewPrice = itemView.findViewById(R.id.textViewSimplePrice);
        }

        public void setName(String name){
            this.textViewName.setText(name);
        }
        public void setPrice(Float price){
            this.textViewPrice.setText(String.valueOf(price));
        }
        public void setTime(String time){
            this.textViewTime.setText(time);
        }
        public void setImageSrc(String link){
            // TODO Glide implementation
        }
    }
}
