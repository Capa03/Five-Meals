package com.example.fivemealsmobileproject.ui.home.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.models.order.InsertOrderProductRequest;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;
import com.example.fivemealsmobileproject.datasource.repository.favorite.FavoriteRepository;
import com.example.fivemealsmobileproject.datasource.repository.order.OrderRepository;
import com.example.fivemealsmobileproject.datasource.repository.product.ProductsRepository;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;
import com.example.fivemealsmobileproject.datasource.room.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeProductDetailsFragmentViewModel extends AndroidViewModel {
    private final MutableLiveData<Integer> quantityLiveData = new MutableLiveData<>();
    private int quantity;
    private long productId;

    private FavoriteRepository favoriteRepository;
    private ProductsRepository productsRepository;
    private OrderRepository orderRepository;
    private AuthRepository authRepository;

    public HomeProductDetailsFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepositories(Activity activity){
        this.productsRepository = new ProductsRepository(activity);
        this.orderRepository = new OrderRepository(activity);
        this.authRepository = new AuthRepository(activity);
        this.favoriteRepository = new FavoriteRepository(activity);
    }

    public LiveData<Product> getProduct(long productId){
        this.productId = productId;
        return this.productsRepository.getProductById(productId);
    }

    public LiveData<Integer> getQuantity() { return this.quantityLiveData; }

    public void incrementQuantity(){ this.quantityLiveData.postValue(++quantity); }

    public void decrementQuantity(){ this.quantityLiveData.postValue(--quantity); }

    public void resetQuantity(){
        this.quantity = 1;
        this.quantityLiveData.postValue(quantity);
    }
    public void addProducts(){
        List<InsertOrderProductRequest> productsToAdd = new ArrayList<>();
        for(int i = 1; i<= quantity; i++){
            productsToAdd.add(new InsertOrderProductRequest(
                    this.authRepository.getSavedEmail(),
                    this.orderRepository.getSavedOrderId(),
                    this.productId
            ));
        }
        this.orderRepository.insertOrderProducts(productsToAdd);
    }

    public LiveData<FavoriteProduct> getFavoriteProduct(){
        return this.favoriteRepository.getFavoriteLiveDataById(this.productId);
    }


    public void favoriteClicked() {
        this.favoriteRepository.toggleFavorite(this.productId);
    }
}
