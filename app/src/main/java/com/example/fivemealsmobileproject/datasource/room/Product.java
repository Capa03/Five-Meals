package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String description;
    private String categoryName;
    private Float price;
    private float minTime;
    private float maxTime;
    private String imgLink;
    private long restaurantId;

    public Product(String name, Float price, float minTime, float maxTime, String categoryName, long restaurantId, String imgLink, String description) {
        this.name = name;
        this.price = price;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.categoryName = categoryName;
        this.imgLink = imgLink;
        this.description = description;
        this.restaurantId = restaurantId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Float getPrice() {
        return price;
    }

    public float getMinTime() {
        return minTime;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public String getImgLink() {
        return imgLink;
    }

    public long getRestaurantId() {
        return restaurantId;
    }
}
