package com.example.fivemealsmobileproject.datasource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderProductDAO {

    @Query("SELECT * FROM OrderProduct WHERE orderId = :orderId AND NOT OrderProduct.paid AND OrderProduct.delivered")
    LiveData<List<OrderProduct>> getAllUnpaidDeliveredProducts(long orderId);

    @Query("SELECT * FROM OrderProduct WHERE orderId = :orderId GROUP BY productID")
    LiveData<List<OrderProduct>> getAllProductsNoDupes(long orderId);

    @Query("SELECT COUNT(productID) FROM OrderProduct WHERE productID = :productID GROUP BY productID")
    LiveData<Integer> getQuantityFromID(long productID);

    @Query("SELECT * FROM OrderProduct WHERE productID = :productID")
    LiveData<List<OrderProduct>> getAllFromID(long productID);

    @Query("DELETE FROM OrderProduct")
    void clearCurrentOrder();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrderProducts(List<OrderProduct> orderProduct);

    @Update
    void updateOrderProduct(OrderProduct orderProduct);

    @Delete
    void deleteOrderProduct(OrderProduct orderProduct);
}
