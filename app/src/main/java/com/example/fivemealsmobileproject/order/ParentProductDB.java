package com.example.fivemealsmobileproject.order;

import android.content.Context;

import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.OrderProduct;
import com.example.fivemealsmobileproject.login.SessionManager;
import com.example.fivemealsmobileproject.main.TableInfo;

import java.util.ArrayList;
import java.util.List;

public class ParentProductDB {

    private static List<ParentProduct> products = new ArrayList<>();

    private ParentProductDB(){}

    public static List<ParentProduct> getAll(Context context){
        if(products.isEmpty()){
            List<OrderProduct> orderProducts = AppDataBase.getInstance(context).getOrderProductDAO().getAllProductsNoDupes(
                    SessionManager.getActiveSession(context),
                    TableInfo.getTable().getTableID()
            );
            for(OrderProduct product: orderProducts){
                products.add(new ParentProduct(product.getProductID()));
            }
        }
        return products;
    }

    public static void addProduct(long productID){
        boolean exists = false;
        for (ParentProduct product:products) {
            if (product.getProductID() == productID) {
                exists = true;
                break;
            }
        }
        if(!exists){
            products.add(new ParentProduct(productID));
        }
    }


    public static void removeProduct(Context context, long productID){
        int quantity = AppDataBase.getInstance(context).getOrderProductDAO().getAllFromID(productID).size();
        if(quantity == 0){
            for (int i = 0; i< products.size(); i++) {
                ParentProduct product = products.get(i);
                if(product.getProductID() == productID){
                    // AVOID "java.util.ConcurrentModificationException"
                    products.remove(i);
                    i--;
                }
            }
        }
    }
    public static void clear(){
        products = new ArrayList<>();
    }
}
