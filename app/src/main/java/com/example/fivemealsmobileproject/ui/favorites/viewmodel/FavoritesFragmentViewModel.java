package com.example.fivemealsmobileproject.ui.favorites.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.repository.favorite.FavoriteRepository;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;

import java.util.List;

public class FavoritesFragmentViewModel extends AndroidViewModel {
    private FavoriteRepository favoriteRepository;

    public FavoritesFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void initializeRepository(Activity activity){
        this.favoriteRepository = new FavoriteRepository(activity);
    }

    public LiveData<List<FavoriteProduct>> getFavoritesLiveData(){
        return this.favoriteRepository.getFavoritesLiveData();
    }

    public void refreshData(){
        this.favoriteRepository.refreshFavorites();
    }
}
