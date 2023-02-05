package com.example.fivemealsmobileproject.datasource.models;

public class FavoriteToggleDTO {
    private long productID ;
    private String userEmail ;

    public FavoriteToggleDTO(long productID, String userEmail) {
        this.productID = productID;
        this.userEmail = userEmail;
    }

    public long getProductID() {
        return productID;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
