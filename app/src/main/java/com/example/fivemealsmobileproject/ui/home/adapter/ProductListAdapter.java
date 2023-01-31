package com.example.fivemealsmobileproject.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.ui.main.ProgressHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<Product> products = new ArrayList<>();
    private final ProductListEventListener productListEventListener;

    public ProductListAdapter(ProductListEventListener productListEventListener){
        this.productListEventListener = productListEventListener;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_simple, parent, false);
        return new ProductListViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Product product = this.products.get(position);

        holder.setName(product.getName());
        int minTime = (int) product.getMinTime();
        int maxTime = (int) product.getMaxTime();
        holder.setTime(ProgressHelper.getTimeToString(minTime, maxTime));
        holder.setPrice(product.getPrice());

        holder.setImage(product.getImgLink());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListEventListener.onProductClick(product.getId());
            }
        });
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
        private final Context context;
        private ImageView productImage;
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewPrice;
        private View itemView;
        private final ProgressBar progressBar;


        public ProductListViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            this.productImage = itemView.findViewById(R.id.imageViewSimpleProduct);
            this.textViewName = itemView.findViewById(R.id.textViewSimpleProductName);
            this.textViewTime = itemView.findViewById(R.id.textViewSimpleAverageTime);
            this.textViewPrice = itemView.findViewById(R.id.textViewSimplePrice);
            this.progressBar = (ProgressBar) itemView.findViewById(R.id.progress);

            this.productImage.setClipToOutline(true);
        }

        public void setName(String name){
            this.textViewName.setText(name);
        }
        public void setPrice(Float price){
            this.textViewPrice.setText((String.valueOf(price) + " €"));
        }
        public void setTime(String time){
            this.textViewTime.setText(time + " min");
        }

        public void setImage(String imageLink){
            Glide.with(this.context).load(imageLink).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    System.out.println(e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(this.productImage);
        }
    }

    public interface ProductListEventListener{
        void onProductClick(long productId);
    }
}
