package com.example.fivemealsmobileproject.database;

import android.content.Context;

public class SingleUseDataBase {


    private SingleUseDataBase(){
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
        String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        if(categoryDAO.getAllCategories().isEmpty()){
            categoryDAO.insertCategory(new Category("Carne"));
            productDAO.insertProduct(new Product("Bifana", 3.5f, 0.5, 1, "Carne",
                    "1_0zXE1wScxdn0DbOXYKE-CgpM7y1BtFI",description));
            productDAO.insertProduct( new Product("Burger", 4.5f, 10, 20, "Carne",
                    "1c-MFHH_qZew23MctTSD5awnbXInElt9S",description));
            productDAO.insertProduct(new Product("Bitoque", 6f, 15, 0, "Carne",
                    "1LQGxf3P06aSjaF1CsdYDb0xPnA2jp5_p",description));
            productDAO.insertProduct(new Product("Pica Pau", 7f, 15, 0, "Carne",
                    "147cNPkB0OcP5u4bRthoR6ci7At0CujhA",description));

            categoryDAO.insertCategory(new Category("Peixe"));
            productDAO.insertProduct(new Product("Salm??o", 5.5f, 10, 0, "Peixe",
                    "1SmNbsAumK8EyMUYKMcckzNmqJJpDHO1B",description));
            productDAO.insertProduct(new Product("Cavala", 5f, 10, 0, "Peixe",
                    "1wLjDQC0r2HDlwEOE7ESL3dQ3rxSOdIfo",description));
            productDAO.insertProduct(new Product("Sardinhas", 7f, 15, 0, "Peixe",
                    "1LIxNKqxd4Pm4BIW6LQD0lyIZKG7-N2jw",description));

            categoryDAO.insertCategory(new Category("Vegetariano"));
            productDAO.insertProduct(new Product("Tofu", 6f, 10, 0, "Vegetariano",
                    "1el9d-UigHTeofLidLGsUdzvgAUcJVF2n",description));
            productDAO.insertProduct(new Product("Salada", 3f, 5, 0, "Vegetariano",
                    "1oVeMz6LFflskxZTCNgP9TWAwkoFhpnyo",description));

            categoryDAO.insertCategory(new Category("Bebidas"));
            productDAO.insertProduct(new Product("Coca-cola", 1.5f, 0, 0, "Bebidas",
                    "1NiqopfKfi1T3jkdmk22o-uEqVyiKD-Je",description));
            productDAO.insertProduct(new Product("Sumol", 1.5f, 0, 0, "Bebidas",
                    "1QZRDXI3wP1fbrnD0nB-75GDj13e-0-iZ",description));

            categoryDAO.insertCategory(new Category("Entradas"));
            productDAO.insertProduct(new Product("Queijo", 2f, 2, 0, "Entradas",
                    "1ZsxgrHaG2WZGrqoOqE32kDmhQ4XhTLMf",description));
            productDAO.insertProduct(new Product("Azeitoas", 1f, 1, 0, "Entradas",
                    "1GJ-RYLa646927CSjglz5YFjtri_LcS13",description));
        }
    }

}
