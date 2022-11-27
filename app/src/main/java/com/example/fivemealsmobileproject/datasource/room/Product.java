package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String description;
    private String category;
    private Float price;
    private float minAverageTime;
    private float maxAverageTime;
    private String imgLink;
    private long restaurantId;

    public Product(String name, Float price, float minAverageTime, float maxAverageTime,  String category, long restaurantId, String imgLink, String description) {
        this.name = name;
        this.price = price;
        this.minAverageTime = minAverageTime;
        this.maxAverageTime = maxAverageTime;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public Float getPrice() {
        return price;
    }

    public float getMinAverageTime() {
        return minAverageTime;
    }

    public float getMaxAverageTime() {
        return maxAverageTime;
    }

    public String getImgLink() {
        return imgLink;
    }

    public long getRestaurantId() {
        return restaurantId;
    }
}
