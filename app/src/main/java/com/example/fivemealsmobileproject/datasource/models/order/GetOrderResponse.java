package com.example.fivemealsmobileproject.datasource.models.order;

public class GetOrderResponse {

    private final int id;
    private final int tableId;
    private final String created;
    private final boolean open;

    public GetOrderResponse(int id, int tableId, String created, boolean open) {
        this.id = id;
        this.tableId = tableId;
        this.created = created;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public String getCreated() {
        return created;
    }

    public boolean isOpen() {
        return open;
    }
}
