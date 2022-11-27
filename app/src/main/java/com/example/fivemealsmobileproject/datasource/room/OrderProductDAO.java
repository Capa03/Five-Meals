package com.example.fivemealsmobileproject.datasource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fivemealsmobileproject.ui.payment.PaymentProduct;

import java.util.List;

@Dao
public interface OrderProductDAO {

    @Query("SELECT * FROM OrderProduct")
    List<OrderProduct> getAllProducts();

    @Query("SELECT * FROM OrderProduct WHERE tableID = :tableID GROUP BY productID")
    LiveData<List<OrderProduct>> getAllProductsNoDupes(long tableID);

    @Query("SELECT COUNT(productID) FROM OrderProduct WHERE productID = :productID GROUP BY productID")
    LiveData<Integer> getQuantityFromID(long productID);

    @Query("SELECT * FROM OrderProduct WHERE productID = :productID")
    LiveData<List<OrderProduct>> getAllFromID(long productID);

    @Query("DELETE FROM OrderProduct")
    void clearCurrentOrder();

    @Query("SELECT Product.name as productName, COUNT(OrderProduct.productID) as quantity, Product.price as unitPrice " +
            "FROM OrderProduct " +
            "INNER JOIN Product ON " +
            "Product.id = OrderProduct.productID " +
            "WHERE OrderProduct.username = :username AND OrderProduct.tableID = :restaurantId " +
            "GROUP BY productID")
    List<PaymentProduct> getAllPaymentProducts(String username, long restaurantId);



    @Insert
    void insertOrderProduct(OrderProduct orderProduct);

    @Update
    void updateOrderProduct(OrderProduct orderProduct);

    @Delete
    void deleteOrderProduct(OrderProduct orderProduct);
}
