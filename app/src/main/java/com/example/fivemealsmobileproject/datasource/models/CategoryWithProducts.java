package com.example.fivemealsmobileproject.datasource.models;

import com.example.fivemealsmobileproject.datasource.room.Product;

import java.util.List;

public class CategoryWithProducts {
    private int id;
    private int restaurantId ;
    private String categoryName ;
    private List<Product> products ;

    public CategoryWithProducts(int id, int restaurantId, String categoryName, List<Product> products) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.categoryName = categoryName;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }
}
