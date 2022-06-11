package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductWaitingForOrderDAO {

    @Query("SELECT * FROM ProductWaitingForOrder")
    List<ProductWaitingForOrder> getAllProducts();

    @Query("SELECT quantity FROM ProductWaitingForOrder WHERE productID = :productID")
    int getQuantityFromID(long productID);

    @Query("DELETE FROM ProductWaitingForOrder")
    void clearCurrentOrder();

    @Insert
    void insertProductQuantity(ProductWaitingForOrder productWaitingForOrder);

    @Update
    void updateQuantity(ProductWaitingForOrder productWaitingForOrder);
}
