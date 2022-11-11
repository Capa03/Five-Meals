package com.example.fivemealsmobileproject.domain;

import com.example.fivemealsmobileproject.datasource.room.Category;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.domain.models.CategoryWithProducts;
import com.example.fivemealsmobileproject.domain.models.Login;
import com.example.fivemealsmobileproject.domain.models.UserCreateDTO;
import com.example.fivemealsmobileproject.datasource.room.Restaurant;
import com.example.fivemealsmobileproject.datasource.room.Table;
import com.example.fivemealsmobileproject.domain.models.UserShowDTO;
import com.example.fivemealsmobileproject.ui.order.ParentProduct;

import java.util.List;

public interface IDomain {

    boolean createUser(UserCreateDTO userCreateDTO);
    boolean checkLogin(Login login);
    UserShowDTO getCurrentUser();


    Table getTable(long tableId);
    Restaurant getRestaurant(long restaurantId);
    Table getCacheTable();
    Restaurant getCacheRestaurant();

    List<Category> getAllCategoriesFromRestaurant(long restaurantId);
    List<Product> getProductsFromCategoryAndRestaurant(String category, long restaurantId);

    List<CategoryWithProducts> getCategoriesWithProductsFromRestaurant(long restaurantId);

    Product getProductById(long productId);

    void insertOrderProducts(long productId, int quantity, boolean forLater);
    void removeOrderProduct(long orderProductId);
    void orderProductNow(long orderProductId);
    List<OrderProduct> getAllOrderProductsFromID(long productID);
    List<ParentProduct> getAllParentProducts();

}
