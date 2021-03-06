package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fivemealsmobileproject.payment.PaymentProduct;

import java.util.List;

@Dao
public interface OrderProductDAO {

    @Query("SELECT * FROM OrderProduct")
    List<OrderProduct> getAllProducts();

    @Query("SELECT * FROM OrderProduct GROUP BY productID")
    List<OrderProduct> getAllProductsNoDupes();

    @Query("SELECT * FROM OrderProduct WHERE productID = :productID")
    List<OrderProduct> getAllFromID(long productID);

    @Query("DELETE FROM OrderProduct")
    void clearCurrentOrder();

    @Query("SELECT Product.name as productName, COUNT(OrderProduct.productID) as quantity, Product.price as unitPrice FROM OrderProduct  INNER JOIN Product ON Product.id = OrderProduct.productID GROUP BY productID")
    List<PaymentProduct> getAllPaymentProducts();



    @Insert
    void insertOrderProduct(OrderProduct orderProduct);

    @Update
    void updateOrderProduct(OrderProduct orderProduct);

    @Delete
    void deleteOrderProduct(OrderProduct orderProduct);
}
