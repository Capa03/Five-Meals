package com.example.fivemealsmobileproject.ui.account;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;
import com.example.fivemealsmobileproject.datasource.repository.favorite.FavoriteRepository;
import com.example.fivemealsmobileproject.datasource.repository.localization.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.order.OrderRepository;
import com.example.fivemealsmobileproject.datasource.repository.product.ProductsRepository;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;

public class AccountFragmentViewModel extends AndroidViewModel {

    private AuthRepository authRepository;
    private FavoriteRepository favoriteRepository;
    private LocalizationRepository localizationRepository;
    private OrderRepository orderRepository;
    private ProductsRepository productsRepository;

    public AccountFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.authRepository = new AuthRepository(activity);
        this.favoriteRepository = new FavoriteRepository(activity);
        this.localizationRepository = new LocalizationRepository(activity);
        this.orderRepository = new OrderRepository(activity);
        this.productsRepository = new ProductsRepository(activity);
    }

    public void CLEAR_ALL_DATA() {
        this.authRepository.clearEmail();
        this.authRepository.clearPassword();
        this.authRepository.clearToken();

        this.favoriteRepository.clearFavorites();

        this.localizationRepository.clearRestaurantId();
        this.localizationRepository.clearTableId();

        this.orderRepository.clearOrderId();
        this.orderRepository.clearOrderProducts();

        this.productsRepository.clearData();
    }

    public String getSavedEmail() {
        return this.authRepository.getSavedEmail();
    }
}
