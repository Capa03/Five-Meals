package com.example.fivemealsmobileproject.ui.favorites.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProductDAO;
import com.example.fivemealsmobileproject.ui.main.TableInfo;

import java.util.List;

public class FavoritesFragmentViewModel extends AndroidViewModel {
    private FavoriteProductDAO favoriteProductDAO;

    public FavoritesFragmentViewModel(@NonNull Application application) {
        super(application);
        this.favoriteProductDAO = AppDataBase.getInstance(application.getApplicationContext()).getFavoriteProductDAO();
    }

    public LiveData<List<FavoriteProduct>> getFavoritesLiveData(){
        return favoriteProductDAO.getAllFavoritesFromRestaurant(TableInfo.getRestaurant().getRestaurantID()
        );
    }
}
