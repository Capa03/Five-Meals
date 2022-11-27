package com.example.fivemealsmobileproject.domain;

import com.example.fivemealsmobileproject.datasource.room.Category;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.datasource.room.Restaurant;
import com.example.fivemealsmobileproject.datasource.room.Table;
import com.example.fivemealsmobileproject.domain.models.CategoryWithProducts;
import com.example.fivemealsmobileproject.domain.models.LoginCreateDTO;
import com.example.fivemealsmobileproject.domain.models.UserCreateDTO;
import com.example.fivemealsmobileproject.domain.models.UserShowDTO;
import com.example.fivemealsmobileproject.ui.order.ParentOrderProduct;

import java.util.List;

public class Domain implements IDomain{

    private static Domain INSTANCE;
    private final IData iData;
    private Domain(IData iData){
        this.iData = iData;
    }

    @Override
    public boolean createUser(UserCreateDTO userCreateDTO) {
        return false;
    }

    @Override
    public boolean checkLogin(LoginCreateDTO loginCreateDTO) {
        return false;
    }

    @Override
    public UserShowDTO getCurrentUser() {
        return null;
    }

    @Override
    public Table getTable(long tableId) {
        return null;
    }

    @Override
    public Restaurant getRestaurant(long restaurantId) {
        return null;
    }

    @Override
    public Table getCacheTable() {
        return null;
    }

    @Override
    public Restaurant getCacheRestaurant() {
        return null;
    }

    @Override
    public List<Category> getAllCategoriesFromRestaurant(long restaurantId) {
        return null;
    }

    @Override
    public List<Product> getProductsFromCategoryAndRestaurant(String category, long restaurantId) {
        return null;
    }

    @Override
    public List<CategoryWithProducts> getCategoriesWithProductsFromRestaurant(long restaurantId) {
        return null;
    }

    @Override
    public Product getProductById(long productId) {
        return null;
    }

    @Override
    public void insertOrderProducts(long productId, int quantity, boolean forLater) {

    }

    @Override
    public void removeOrderProduct(long orderProductId) {

    }

    @Override
    public void orderProductNow(long orderProductId) {

    }

    @Override
    public List<OrderProduct> getAllOrderProductsFromID(long productID) {
        return null;
    }

    @Override
    public List<ParentOrderProduct> getAllParentProducts() {
        return null;
    }
}
