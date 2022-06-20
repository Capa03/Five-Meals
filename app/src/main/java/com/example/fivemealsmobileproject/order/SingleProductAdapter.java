package com.example.fivemealsmobileproject.order;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.OrderProduct;
import com.example.fivemealsmobileproject.database.Product;

import java.util.List;

public class SingleProductAdapter extends RecyclerView.Adapter<SingleProductAdapter.SingleProductViewHolder> {

    private List<OrderProduct> products;
    private Context context;
    private SingleProductEventListener singleProductEventListener;

    public SingleProductAdapter(SingleProductEventListener singleProductEventListener){
        this.singleProductEventListener = singleProductEventListener;
    }

    @NonNull
    @Override
    public SingleProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_type_product, parent,false);
        return new SingleProductViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleProductViewHolder holder, int position) {
        OrderProduct orderProduct = products.get(position);
        Product product = AppDataBase.getInstance(this.context).getProductDAO().getById(orderProduct.getProductID());
        holder.setName(product.getName());
        holder.setPrice(product.getPrice());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO AlertDialog
                singleProductEventListener.onRemoveProductClick(orderProduct);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateData(List<OrderProduct> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public class SingleProductViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPrice;
        private ImageView removeButton;


        public SingleProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
            this.removeButton = itemView.findViewById(R.id.imageViewOrderRemoveSingleProductButton);
        }

        public void setName(String name){
            this.textViewName.setText(name);
        }

        public void setPrice(float price){
            this.textViewPrice.setText((String.valueOf(price) + " €"));
        }
    }

    public interface SingleProductEventListener{
        void onRemoveProductClick(OrderProduct orderProduct);
    }
}
