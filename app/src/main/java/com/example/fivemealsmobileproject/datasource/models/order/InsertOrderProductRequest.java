package com.example.fivemealsmobileproject.datasource.models.order;

public class InsertOrderProductRequest {
    private String userEmail;
    private int orderId;
    private long productID;

    public InsertOrderProductRequest(String userEmail, int orderId, long productID) {
        this.userEmail = userEmail;
        this.orderId = orderId;
        this.productID = productID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getOrderId() {
        return orderId;
    }

    public long getProductID() {
        return productID;
    }
}
