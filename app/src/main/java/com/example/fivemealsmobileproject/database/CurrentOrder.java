package com.example.fivemealsmobileproject.database;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder {
    // TODO Possible implementation of a second class to add quantity and avoid dupes inside the cart
    public static List<Product> currentOrder = new ArrayList<>();

    private CurrentOrder(){ }

    public static List<Product> getCurrentOrder(){
        return currentOrder;
    }

    public static void addProduct(Product product){
        currentOrder.add(product);
    }

    public static void removeProduct(Product product){
        currentOrder.remove(product);
    }


}
