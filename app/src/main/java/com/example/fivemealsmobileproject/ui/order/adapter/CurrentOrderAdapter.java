package com.example.fivemealsmobileproject.ui.order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.ui.main.TimeHelper;
import com.example.fivemealsmobileproject.ui.order.ParentOrderProduct;


import java.util.ArrayList;
import java.util.List;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderViewHolder> implements SingleProductAdapter.SingleProductEventListener {

    private final ParentProductEventListener parentProductEventListener;
    private List<ParentOrderProduct> products = new ArrayList<>();
    private LifecycleOwner observerOwner;

    public CurrentOrderAdapter(LifecycleOwner observerOwner, ParentProductEventListener parentProductEventListener){
        this.observerOwner = observerOwner;
        this.parentProductEventListener = parentProductEventListener;
    }

    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_product_parent,parent,false);
        return new CurrentOrderViewHolder(layout, parent.getContext(), this);
    }



    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {

            ParentOrderProduct parentOrderProduct = products.get(position);

            parentOrderProduct.getOrderProductsLiveData().observe(this.observerOwner, orderProducts -> {
                holder.adapter.updateData(orderProducts);
            });

            parentOrderProduct.getQuantity().observe(this.observerOwner, quantity -> {
                if(quantity != null) holder.setQuantity(quantity);
            });

            holder.setName(parentOrderProduct.getProductName());
            int minTime = (int) parentOrderProduct.getProductMinTime();
            int maxTime = (int) parentOrderProduct.getProductMaxTime();

            holder.setTime(TimeHelper.getTimeToString(minTime, maxTime));
            holder.setPrice(parentOrderProduct.getProductPrice() * holder.adapter.getItemCount());
            holder.setImage(parentOrderProduct.getImgLink());
            holder.recyclerView.setVisibility(parentOrderProduct.getState());

            holder.itemView.setOnClickListener(view -> {
                int currentState = holder.recyclerView.getVisibility();
                switch (currentState) {
                    case View.GONE:
                        parentOrderProduct.setState(View.VISIBLE);
                        holder.recyclerView.setVisibility(View.VISIBLE);
                        break;
                    case View.VISIBLE:
                        parentOrderProduct.setState(View.GONE);
                        holder.recyclerView.setVisibility(View.GONE);
                        break;
                }
            });
    }

        @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateData(List<ParentOrderProduct> products){
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public void onRemoveProductClick(OrderProduct orderProduct, int position) {
        parentProductEventListener.onRemoveProductClick(orderProduct, position);
    }

    public interface ParentProductEventListener{
        void onRemoveProductClick(OrderProduct orderProduct, int position);
    }

    public class CurrentOrderViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private ImageView currentOrderImage;
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewPrice;
        private TextView textViewQuantity;
        private View itemView;
        private SingleProductAdapter adapter;
        public RecyclerView recyclerView;


        public CurrentOrderViewHolder(@NonNull View itemView, Context context, SingleProductAdapter.SingleProductEventListener eventListener) {
            super(itemView);
                this.context = context;
                this.itemView = itemView;
                this.currentOrderImage = itemView.findViewById(R.id.imageViewCurrentOrder);
                this.currentOrderImage.setClipToOutline(true);
                this.textViewName = itemView.findViewById(R.id.textViewCurrentOrderName);
                this.textViewTime = itemView.findViewById(R.id.textViewCurrentOrderAverageTime);
                this.textViewPrice = itemView.findViewById(R.id.textViewCurrentOrderPrice);
                this.textViewQuantity = itemView.findViewById(R.id.textViewCurrentOrderQuantity);

                this.recyclerView = itemView.findViewById(R.id.recyclerViewOrderSingleTypeProduct);
                this.adapter = new SingleProductAdapter(eventListener);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.context);
                this.recyclerView.setAdapter(this.adapter);
                this.recyclerView.setLayoutManager(layoutManager);
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

        public void setImage(String imageID) {
            // https://drive.google.com/uc?id=
            String link = "https://docs.google.com/uc?id=" + imageID;
            Glide.with(this.context).load(link).into(this.currentOrderImage);
        }

        public void setQuantity(int quantity){
            this.textViewQuantity.setText(quantity + "x");
        }
    }
}

