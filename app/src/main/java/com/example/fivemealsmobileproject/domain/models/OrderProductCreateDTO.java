package com.example.fivemealsmobileproject.domain.models;

public class OrderProductCreateDTO {

    private long tableID;
    private long productID;

    public OrderProductCreateDTO(long tableID, long productID) {
        this.tableID = tableID;
        this.productID = productID;
    }
}
