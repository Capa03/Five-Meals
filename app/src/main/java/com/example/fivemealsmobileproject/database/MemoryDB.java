package com.example.fivemealsmobileproject.database;

import android.content.Context;

public class MemoryDB {


    private MemoryDB(){
        // Private constructor
    }

    public static void populateRestaurantTables(Context context){

        RestaurantDAO restaurantDAO = AppDataBase.getInstance(context).getRestaurantDAO();
        TableDAO tableDAO = AppDataBase.getInstance(context).getTableDAO();
        if(restaurantDAO.getAllRestaurants().isEmpty()){
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantID(1);
            restaurant.setRestaurantName("Pulo do Lobo");
            restaurantDAO.insertRestaurant(restaurant);
            for(int i= 0; i < 10; i++){
                tableDAO.insertTable(new Table(restaurant.getRestaurantID()));
            }
        }
    }

    public static void populateCategoryTable(Context context){
        CategoryDAO categoryDAO = AppDataBase.getInstance(context).getCategoryDAO();
        ProductDAO productDAO = AppDataBase.getInstance(context).getProductDAO();

        if(categoryDAO.getAllCategories().isEmpty()){
            categoryDAO.insertCategory(new Category("Carne"));
            productDAO.insertProduct(new Product("Bifana", 3.5f, 10, "Carne"));
            productDAO.insertProduct( new Product("Hamburguer", 4.5f, 10, "Carne"));
            productDAO.insertProduct(new Product("Bitoque", 6f, 15, "Carne"));
            productDAO.insertProduct(new Product("Pica Pau", 7f, 15, "Carne"));

            categoryDAO.insertCategory(new Category("Peixe"));
            productDAO.insertProduct(new Product("Salmão", 5.5f, 10, "Peixe"));
            productDAO.insertProduct(new Product("Cavala", 5f, 10, "Peixe"));
            productDAO.insertProduct(new Product("Sardinhas", 7f, 15, "Peixe"));

            categoryDAO.insertCategory(new Category("Vegetariano"));
            productDAO.insertProduct(new Product("Tofu", 6f, 10, "Vegetariano"));
            productDAO.insertProduct(new Product("Salada", 3f, 5, "Vegetariano"));

            categoryDAO.insertCategory(new Category("Bebidas"));
            productDAO.insertProduct(new Product("Coca-cola", 1.5f, 0, "Bebidas"));
            productDAO.insertProduct(new Product("Sumol", 1.5f, 0, "Bebidas"));

            categoryDAO.insertCategory(new Category("Entradas"));
            productDAO.insertProduct(new Product("Queijo", 2f, 2, "Entradas"));
            productDAO.insertProduct(new Product("Azeitoas", 1f, 1, "Entradas"));
        }


    }
}
