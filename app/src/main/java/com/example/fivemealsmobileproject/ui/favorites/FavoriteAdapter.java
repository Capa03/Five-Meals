package com.example.fivemealsmobileproject.ui.favorites;

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
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<FavoriteProduct> favoriteProducts;
    private FavoriteEventListener eventListener;

    public FavoriteAdapter(FavoriteEventListener favoriteEventListener, List<FavoriteProduct> favoriteProducts){
        this.eventListener = favoriteEventListener;
        this.favoriteProducts = favoriteProducts;
    }


    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favorite_simple,parent,false);
        return new FavoriteViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        FavoriteProduct favoriteProduct = this.favoriteProducts.get(position);

            holder.setName(favoriteProduct.getProductName());
            holder.setImage(favoriteProduct.getProductImage());
            holder.productImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onFavoriteClick(favoriteProduct.getProductID());
                }
            });

    }

    @Override
    public int getItemCount() {
        return this.favoriteProducts.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private ImageView productImage;
        private TextView textViewName;
        private TextView textViewPrice;
        private View itemView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.productImage = itemView.findViewById(R.id.imageViewFavoriteProduct);
            this.textViewName = itemView.findViewById(R.id.textViewFavoriteProductName);
            this.productImage.setClipToOutline(true);
        }

        public void setName(String name){
            this.textViewName.setText(name);
        }

        public void setImage(String imageID){
            // https://drive.google.com/uc?id=
            String link = "https://docs.google.com/uc?id=" + imageID;
            Glide.with(this.context).load(link).into(this.productImage);
        }
    }
    public interface FavoriteEventListener{
        void onFavoriteClick(long favoriteId);
    }
}
