package com.example.fivemealsmobileproject.datasource.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TableDAO {

    @Query("SELECT * FROM `Table` WHERE restaurantID = :restaurantID")
    List<Table> getAllTablesFromRestaurantID(long restaurantID);
    @Query("SELECT * FROM `Table`")
    List<Table> getAllTables();

    @Query("SELECT * FROM `Table` WHERE tableID = :tableID")
    Table getTableFromID(long tableID);

    @Insert
    void insertTable(Table table);
}
