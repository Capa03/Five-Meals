package com.example.fivemealsmobileproject.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.Category;
import com.example.fivemealsmobileproject.datasource.room.CategoryDAO;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.datasource.room.ProductDAO;
import com.example.fivemealsmobileproject.ui.main.TableInfo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragmentViewModel extends AndroidViewModel {
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        this.categoryDAO = AppDataBase.getInstance(application).getCategoryDAO();
        this.productDAO = AppDataBase.getInstance(application).getProductDAO();
    }

    public LiveData<List<Category>> getCategories(){
        return this.categoryDAO.getAllCategoriesFromRestaurant(TableInfo.getRestaurant().getRestaurantID());
    }

    public LiveData<List<Product>> getProducts(String category) {
        return this.productDAO.getAllFromCategoryAndRestaurant(category, TableInfo.getRestaurant().getRestaurantID());
    }
}
