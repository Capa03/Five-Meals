package com.example.fivemealsmobileproject.home;

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
import com.example.fivemealsmobileproject.database.Product;
import com.example.fivemealsmobileproject.main.TimeHelper;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<Product> products;
    private ProductListEventListener productListEventListener;

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
        int minTime = (int) product.getMinAverageTime();
        int maxTime = (int) product.getMaxAverageTime();
        holder.setTime(TimeHelper.getTimeToString(minTime, maxTime));
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


        public ProductListViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            this.productImage = itemView.findViewById(R.id.imageViewSimpleProduct);
            this.textViewName = itemView.findViewById(R.id.textViewSimpleProductName);
            this.textViewTime = itemView.findViewById(R.id.textViewSimpleAverageTime);
            this.textViewPrice = itemView.findViewById(R.id.textViewSimplePrice);

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

        public void setImage(String imageID){
            // https://drive.google.com/uc?id=
            String link = "https://docs.google.com/uc?id=" + imageID;
            Glide.with(this.context).load(link).into(this.productImage);
        }
    }

    public interface ProductListEventListener{
        void onProductClick(long productID);
    }
}
