package com.example.fivemealsmobileproject.datasource.room;

import android.content.Context;

public class SingleUseDataBase {


    private SingleUseDataBase(){
        // Private constructor
    }

    public static void populateRestaurantTables(Context context){

        RestaurantDAO restaurantDAO = AppDataBase.getInstance(context).getRestaurantDAO();
        TableDAO tableDAO = AppDataBase.getInstance(context).getTableDAO();
        if(restaurantDAO.getAllRestaurants().isEmpty()){
            Restaurant restaurant1 = new Restaurant(1, "Pulo do Lobo");
            restaurantDAO.insertRestaurant(restaurant1);

            Restaurant restaurant2 = new Restaurant(2, "Burger King do Rossio");
            restaurantDAO.insertRestaurant(restaurant2);

            for(int i= 0; i < 10; i++){
                tableDAO.insertTable(new Table(restaurant1.getRestaurantID()));
            }

            for(int i= 0; i < 10; i++){
                tableDAO.insertTable(new Table(restaurant2.getRestaurantID()));
            }


        }
    }

    public static void populateCategoryTable(Context context){
        CategoryDAO categoryDAO = AppDataBase.getInstance(context).getCategoryDAO();
        ProductDAO productDAO = AppDataBase.getInstance(context).getProductDAO();
        String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        if(categoryDAO.getAllCategories().isEmpty()){
            categoryDAO.insertCategory(new Category(1, "Carne"));
            productDAO.insertProduct(new Product("Bifana", 3.5f, 0.5f, 1, "Carne", 1,
                    "1_0zXE1wScxdn0DbOXYKE-CgpM7y1BtFI",description));
            productDAO.insertProduct( new Product("Burger", 4.5f, 10, 20, "Carne",1,
                    "1c-MFHH_qZew23MctTSD5awnbXInElt9S",description));
            productDAO.insertProduct(new Product("Bitoque", 6f, 15, 0, "Carne",1,
                    "1LQGxf3P06aSjaF1CsdYDb0xPnA2jp5_p",description));
            productDAO.insertProduct(new Product("Pica Pau", 7f, 15, 0, "Carne",1,
                    "147cNPkB0OcP5u4bRthoR6ci7At0CujhA",description));

            categoryDAO.insertCategory(new Category(1, "Peixe"));
            productDAO.insertProduct(new Product("Salmão", 5.5f, 10, 0, "Peixe",1,
                    "1SmNbsAumK8EyMUYKMcckzNmqJJpDHO1B",description));
            productDAO.insertProduct(new Product("Cavala", 5f, 10, 0, "Peixe",1,
                    "1wLjDQC0r2HDlwEOE7ESL3dQ3rxSOdIfo",description));
            productDAO.insertProduct(new Product("Sardinhas", 7f, 15, 0, "Peixe",1,
                    "1LIxNKqxd4Pm4BIW6LQD0lyIZKG7-N2jw",description));

            categoryDAO.insertCategory(new Category(1, "Vegetariano"));
            productDAO.insertProduct(new Product("Tofu", 6f, 10, 0, "Vegetariano",1,
                    "1el9d-UigHTeofLidLGsUdzvgAUcJVF2n",description));
            productDAO.insertProduct(new Product("Salada", 3f, 5, 0, "Vegetariano",1,
                    "1oVeMz6LFflskxZTCNgP9TWAwkoFhpnyo",description));

            categoryDAO.insertCategory(new Category(1, "Bebidas"));
            productDAO.insertProduct(new Product("Coca-cola", 1.5f, 0, 0, "Bebidas",1,
                    "1NiqopfKfi1T3jkdmk22o-uEqVyiKD-Je",description));
            productDAO.insertProduct(new Product("Sumol", 1.5f, 0, 0, "Bebidas",1,
                    "1QZRDXI3wP1fbrnD0nB-75GDj13e-0-iZ",description));

            categoryDAO.insertCategory(new Category(1, "Entradas"));
            productDAO.insertProduct(new Product("Queijo", 2f, 2, 0, "Entradas",1,
                    "1ZsxgrHaG2WZGrqoOqE32kDmhQ4XhTLMf",description));
            productDAO.insertProduct(new Product("Azeitoas", 1f, 1, 0, "Entradas",1,
                    "1GJ-RYLa646927CSjglz5YFjtri_LcS13",description));


            categoryDAO.insertCategory(new Category(2, "Originals"));
            productDAO.insertProduct(new Product("Menu Ibérico", 3.5f, 0.5f, 1, "Originals",2,
                    "1yDivLVMYY9FgvhG6fqwBSwvSl_2MzBo8",description));
            productDAO.insertProduct( new Product("Quejo & Caramelo", 4.5f, 10, 20, "Originals",2,
                    "1oSZzEgefXK7nk3BpSmMm2ksjCVbPycs6",description));
            productDAO.insertProduct(new Product("The King Bacon", 6f, 15, 0, "Originals",2,
                    "1irHfyy4KQW1WkWEraT6yARnt3FSDYkBN",description));
            productDAO.insertProduct(new Product("Whopper", 7f, 15, 0, "Originals",2,
                    "14NcggEzWaZSXlu1mP6UMmmtVkMoSVpWz",description));

            categoryDAO.insertCategory(new Category(2, "Chicken"));
            productDAO.insertProduct(new Product("Queen Cheese", 5.5f, 10, 0, "Chicken",2,
                    "148-H-K9QZ8yufY75BeRMa6gS5EKliPLE",description));
            productDAO.insertProduct(new Product("Chicken Tendercrisp", 5f, 10, 0, "Chicken",2,
                    "1x4YXrc0aVZIqtNOoy97dPbH8EROH8qB5",description));
            productDAO.insertProduct(new Product("Nuggets(x9)", 7f, 15, 0, "Chicken",2,
                    "1J5A9m0qxk8v9bplpG0uJrEMtJHVh9Mb-",description));

            categoryDAO.insertCategory(new Category(2, "Vegan"));
            productDAO.insertProduct(new Product("Long Vegan", 6f, 10, 0, "Vegan",2,
                    "1ViTJa45GgjrPNah9RaoOIgFkrIPBN62e",description));
            productDAO.insertProduct(new Product("Big King Vegan", 3f, 5, 0, "Vegan",2,
                    "1lS6JgpyTzvn2_fLD7SoCjHGqFymoGgox",description));

            categoryDAO.insertCategory(new Category(2, "Drinks"));
            productDAO.insertProduct(new Product("Coca-cola", 1.5f, 0, 0, "Drinks",2,
                    "1NiqopfKfi1T3jkdmk22o-uEqVyiKD-Je",description));
            productDAO.insertProduct(new Product("Orange Juice", 1.5f, 0, 0, "Drinks",2,
                    "1WEIwlyMseu58y9K1sd2caFMERsTHxbdw",description));

            categoryDAO.insertCategory(new Category(2, "Entries"));
            productDAO.insertProduct(new Product("Cheese Bites", 2f, 2, 0, "Entries",2,
                    "1vHo-ne7LfEH5826NDfkJmnZxHGUaxUGg",description));
            productDAO.insertProduct(new Product("Bucket", 1f, 1, 0, "Entries",2,
                    "1s90-bd7774dTZHtwckMj-pgC7epgB21P",description));
        }
    }

}
