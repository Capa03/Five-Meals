package com.example.fivemealsmobileproject.database;

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
    private double minAverageTime;
    private double maxAverageTime;
    private String imgLink;

    public Product(String name, Float price, double minAverageTime, double maxAverageTime,  String category, String imgLink, String description) {
        this.name = name;
        this.price = price;
        this.minAverageTime = minAverageTime;
        this.maxAverageTime = maxAverageTime;
        this.category = category;
        this.imgLink = imgLink;
        this.description = description;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public double getMinAverageTime() {
        return minAverageTime;
    }

    public void setMinAverageTime(int minAverageTime) {
        this.minAverageTime = minAverageTime;
    }

    public double getMaxAverageTime() {
        return maxAverageTime;
    }

    public void setMaxAverageTime(int maxAverageTime) {
        this.maxAverageTime = maxAverageTime;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }



}
