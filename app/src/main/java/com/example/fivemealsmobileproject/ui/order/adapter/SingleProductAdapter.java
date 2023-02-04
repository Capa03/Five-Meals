package com.example.fivemealsmobileproject.ui.order.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.ui.main.ProgressHelper;

import java.util.ArrayList;
import java.util.List;

public class SingleProductAdapter extends RecyclerView.Adapter<SingleProductAdapter.BaseProductViewHolder> {

    private final int TYPE_IN_QUEUE = 0;
    private final int TYPE_IN_PROGRESS = 1;
    private final int TYPE_DELIVERED = 2;
    private final int TYPE_PAID = 3;

    private List<OrderProduct> products = new ArrayList<>();
    private Context context;
    private final SingleProductEventListener singleProductEventListener;

    public interface SingleProductEventListener {
        void onRemoveProductClick(OrderProduct orderProduct);
    }

    public SingleProductAdapter(SingleProductEventListener singleProductEventListener) {
        this.singleProductEventListener = singleProductEventListener;
    }


    @Override
    public int getItemViewType(int position) {
        OrderProduct orderProduct = products.get(position);
        if(orderProduct.getStepsMade() == 0){
            return TYPE_IN_QUEUE;
        }
        if(orderProduct.isPaid()){
            return TYPE_PAID;
        }
        if(orderProduct.isDelivered()){
            return TYPE_DELIVERED;
        }
        return TYPE_IN_PROGRESS;
    }

    @NonNull
    @Override
    public BaseProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        switch (viewType){
            case TYPE_IN_QUEUE:
                View layout1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_in_queue, parent, false);
                return new InQueueProductViewHolder(layout1);
            case TYPE_DELIVERED:
                View layout2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_delivered, parent, false);
                return new DeliveredProductViewHolder(layout2);
            case TYPE_PAID:
                View layout3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_paid, parent, false);
                return new PaidProductViewHolder(layout3);

        }
        // In progress
        View layout4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_in_progress, parent, false);
        return new InProgressProductViewHolder(layout4);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseProductViewHolder holder, int position) {
        OrderProduct orderProduct = products.get(position);

        holder.setName(orderProduct.getProductName());
        holder.setPrice(orderProduct.getProductPrice());


        if(holder instanceof InQueueProductViewHolder){
            InQueueProductViewHolder inQueueProductViewHolder = (InQueueProductViewHolder) holder;
            inQueueProductViewHolder.removeButton.setOnClickListener(removeButtonView -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(context.getString(R.string.delete_order));
                builder.setMessage(context.getString(R.string.delete_order_confirmation));
                builder.setPositiveButton(context.getString(R.string.yes), (dialog, which) -> singleProductEventListener.onRemoveProductClick(orderProduct));
                builder.setNegativeButton(context.getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.show();
            });
        } else if(holder instanceof InProgressProductViewHolder){
            InProgressProductViewHolder inProgressProductViewHolder = (InProgressProductViewHolder) holder;
            int progress = ProgressHelper.getProgressInPercentage(orderProduct.getStepsMade(),orderProduct.getMaxSteps());
            inProgressProductViewHolder.setProgressbar(100 - progress);
            inProgressProductViewHolder.setProgressText(progress + "%");
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateData(List<OrderProduct> products) {
        this.products = products;
        notifyDataSetChanged();
    }


    public abstract class BaseProductViewHolder extends RecyclerView.ViewHolder {
        public BaseProductViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void setName(String name);

        public abstract void setPrice(float price);
    }

    public class InQueueProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;
        private final ImageView removeButton;


        public InQueueProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
            this.removeButton = itemView.findViewById(R.id.imageViewOrderRemoveSingleProductButton);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((price + " €"));
        }

    }

    public class InProgressProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;
        private final TextView textViewTimePassed;
        private final ProgressBar progressBar;

        public InProgressProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
            this.progressBar = itemView.findViewById(R.id.progressBarOrderSingleProductTimeTaken);
            this.textViewTimePassed = itemView.findViewById(R.id.textViewOrderSingleProductTimePassed);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((price + " €"));
        }

        public void setProgressbar(int progress) {
            this.progressBar.setProgress(progress);
        }

        public void setProgressText(String text) {
            this.textViewTimePassed.setText(text);
        }
    }

    public class DeliveredProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;

        public DeliveredProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((String.valueOf(price) + " €"));
        }
    }

    public class PaidProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;

        public PaidProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
        }
        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((price + " €"));
        }

    }
}
