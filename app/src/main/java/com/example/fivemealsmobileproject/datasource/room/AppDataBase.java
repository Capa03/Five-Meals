package com.example.fivemealsmobileproject.datasource.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fivemealsmobileproject.datasource.models.Table;


@Database(entities = {Product.class, Category.class, OrderProduct.class, FavoriteProduct.class}, version = 1)
    public abstract class AppDataBase extends RoomDatabase {
        public abstract ProductDAO getProductDAO();
        public abstract CategoryDAO getCategoryDAO();
        public abstract OrderProductDAO getOrderProductDAO();
        public abstract FavoriteProductDAO getFavoriteProductDAO();

        private static AppDataBase INSTANCE;

        public static AppDataBase getInstance(Context context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDataBase.class, "AppDatabase")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }

}
