package com.example.fivemealsmobileproject.favorites;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.FavoriteProduct;
import com.example.fivemealsmobileproject.home.HomeFragmentDirections;
import com.example.fivemealsmobileproject.home.HomeProductDetailsFragmentArgs;
import com.example.fivemealsmobileproject.main.MainActivity;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoriteAdapter.FavoriteEventListener {
    private Context context;
    private FavoriteAdapter adapter;
    private View view;
    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                MainActivity.startActivity(context,0);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        ImageView imageViewGoBack = view.findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewfavoriteFragment);
        List<FavoriteProduct> favoriteProducts = AppDataBase.getInstance(this.context).getFavoriteProductDAO().getAllFavorite();
        this.adapter = new FavoriteAdapter(this,favoriteProducts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.context);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onFavoriteClick(long productID) {
        NavDirections action = (NavDirections) FavoritesFragmentDirections.actionFavoritesFragmentToHomeProductDetailsFragment(productID);
        Navigation.findNavController(this.view).navigate(action);
    }
}