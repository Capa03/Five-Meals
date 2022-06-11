package com.example.fivemealsmobileproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductWithQuantityDAO {

    @Query("SELECT * FROM ProductWithQuantity")
    List<ProductWithQuantity> getAllProducts();

    @Query("SELECT quantity FROM Productwithquantity WHERE productID = :productID")
    int getQuantityFromID(long productID);

    @Query("DELETE FROM ProductWithQuantity")
    void clearCurrentOrder();

    @Insert
    void insertProductQuantity(ProductWithQuantity productWithQuantity);

    @Update
    void updateQuantity(ProductWithQuantity productWithQuantity);
}
