package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderProductDAO {

    @Query("SELECT * FROM OrderProduct WHERE later")
    List<OrderProduct> getAllLaterProducts();
    @Query("SELECT * FROM OrderProduct WHERE NOT later")
    List<OrderProduct> getAllOrderedProducts();

    @Query("SELECT quantity FROM OrderProduct WHERE productID = :productID")
    int getQuantityFromID(long productID);

    @Query("DELETE FROM OrderProduct")
    void clearCurrentOrder();

    @Insert
    void insertProductQuantity(OrderProduct orderProduct);

    @Update
    void updateQuantity(OrderProduct orderProduct);
}
