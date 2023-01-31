package com.example.fivemealsmobileproject.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.Category;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.ui.home.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> implements ProductListAdapter.ProductListEventListener {

    private List<Category> categories = new ArrayList<>();
    private final CategoryListEventListener categoryEventListener;
    private final LifecycleOwner observerOwner;

    public CategoryListAdapter(LifecycleOwner observerOwner, CategoryListEventListener categoryEventListener) {
        this.categoryEventListener = categoryEventListener;
        this.observerOwner = observerOwner;
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        if(viewType == HomeFragment.FIRST_ITEM ){
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_first_category, parent, false);
        }else {
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        }
        return new CategoryListViewHolder(layout, this);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HomeFragment.FIRST_ITEM : 1;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListViewHolder holder, int position) {
        String categoryName = categories.get(position).getCategoryName();
        holder.setTitle(categoryName);
        categoryEventListener.getProducts(categoryName).observe(observerOwner, products -> {
            holder.adapter.updateData(products);
        });

    }

    public void updateData(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }


    public static class CategoryListViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final ProductListAdapter adapter;

        public CategoryListViewHolder(@NonNull View itemView, ProductListAdapter.ProductListEventListener productListEventListener) {
            super(itemView);

            this.textViewTitle = itemView.findViewById(R.id.textViewCardCategoryTitle);
            //TODO Resolver o "bug" de não guardar o estado da recycler view
            //                // (possivelmente focar no ultimo item clicado)
            this.adapter = new ProductListAdapter(productListEventListener);
            RecyclerView recyclerView = itemView.findViewById(R.id.recyclerViewCardCategoryProductList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setAdapter(this.adapter);
            recyclerView.setLayoutManager(layoutManager);

        }

        public void setTitle(String title) {
            this.textViewTitle.setText(title);
        }
    }

    @Override
    public void onProductClick(long productId) {
        this.categoryEventListener.onProductClick(productId);
    }

    public interface CategoryListEventListener {
        void onProductClick(long productId);
        LiveData<List<Product>> getProducts(String category);
    }
}
