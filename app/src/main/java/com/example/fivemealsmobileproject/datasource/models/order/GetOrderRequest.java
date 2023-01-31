package com.example.fivemealsmobileproject.datasource.models.order;

public class GetOrderRequest {
    private final int tableID;

    public GetOrderRequest(int tableID) {
        this.tableID = tableID;
    }

    public int getTableID() {
        return tableID;
    }
}
