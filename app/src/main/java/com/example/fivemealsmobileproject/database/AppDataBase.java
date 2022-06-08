package com.example.fivemealsmobileproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


    @Database(entities = {User.class, Table.class, Restaurant.class, Product.class, Category.class}, version = 1)
    public abstract class AppDataBase extends RoomDatabase {

        public abstract UserDAO getUserDAO();
        public abstract TableDAO getTableDAO();
        public abstract RestaurantDAO getRestaurantDAO();
        public abstract ProductDAO getProductDAO();
        public abstract CategoryDAO getCategoryDAO();

        private static AppDataBase INSTANCE;

        public static AppDataBase getInstance(Context context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDataBase.class, "ChatBotDatabase")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }

}
