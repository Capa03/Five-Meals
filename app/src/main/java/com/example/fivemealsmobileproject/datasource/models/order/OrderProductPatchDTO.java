package com.example.fivemealsmobileproject.datasource.models.order;

public class OrderProductPatchDTO {
    public long orderId;
    public long orderProductID;
    public int stepsMade;
    public Boolean paid;
    public Boolean delivered;

    public OrderProductPatchDTO(long orderId, long orderProductID, int stepsMade, Boolean paid, Boolean delivered) {
        this.orderId = orderId;
        this.orderProductID = orderProductID;
        this.stepsMade = stepsMade;
        this.paid = paid;
        this.delivered = delivered;
    }

}
