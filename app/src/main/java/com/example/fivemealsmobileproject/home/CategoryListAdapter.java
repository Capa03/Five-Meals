package com.example.fivemealsmobileproject.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.Category;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> implements ProductListAdapter.ProductListEventListener {

    private List<Category> categories;
    private CategoryListEventListener categoryEventListener;

    public CategoryListAdapter(CategoryListEventListener categoryEventListener, List<Category> categories) {
        this.categoryEventListener = categoryEventListener;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new CategoryListViewHolder(layout, parent.getContext(), this);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListViewHolder holder, int position) {
        String categoryName = categories.get(position).getCategoryName();
        holder.setTitle(categoryName);
        // TODO Razão da recycler view horizontal não manter a posição
        holder.adapter.updateData(AppDataBase.getInstance(holder.context).getProductDAO().getAllFromCategory(categoryName));

        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryEventListener.onCategoryClick(categoryName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }


    public class CategoryListViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView textViewTitle;
        private ProductListAdapter adapter;

        public CategoryListViewHolder(@NonNull View itemView, Context context, ProductListAdapter.ProductListEventListener productListEventListener) {
            super(itemView);
            this.context = context;
            this.textViewTitle = itemView.findViewById(R.id.textViewCardCategoryTitle);
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
    public void onProductClick(long productID) {
        this.categoryEventListener.onProductClick(productID);
    }

    public interface CategoryListEventListener {
        // TODO Change "String categoryName" for "long categoryID" in future
        void onCategoryClick(String categoryName);
        void onProductClick(long productID);
    }
}
