package com.example.fivemealsmobileproject.ui.home.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.repository.localization.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.product.ProductsRepository;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.Category;
import com.example.fivemealsmobileproject.datasource.room.CategoryDAO;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.datasource.room.ProductDAO;

import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {
    private ProductsRepository productsRepository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.productsRepository = new ProductsRepository(activity);
    }

    public LiveData<List<Category>> getCategories(){
        return productsRepository.getCategories();
    }

    public LiveData<List<Product>> getProducts(String category) {
        return productsRepository.getProducts(category);
    }
}
