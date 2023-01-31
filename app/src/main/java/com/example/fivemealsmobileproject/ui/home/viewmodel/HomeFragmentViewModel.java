package com.example.fivemealsmobileproject.ui.home.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.repository.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.product.ProductsRepository;
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
    private LocalizationRepository localizationRepository;
    private ProductsRepository productsRepository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        this.categoryDAO = AppDataBase.getInstance(application).getCategoryDAO();
        this.productDAO = AppDataBase.getInstance(application).getProductDAO();
    }

    public void initializeRepository(Activity activity){
        this.localizationRepository = new LocalizationRepository(activity);
        this.productsRepository = new ProductsRepository(activity);
    }

    public LiveData<List<Category>> getCategories(){
        return productsRepository.getCategories();
    }

    public LiveData<List<Product>> getProducts(String category) {
        return productsRepository.getProducts(category);
    }
}
