package com.example.fivemealsmobileproject.ui.favorites;

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
import android.widget.TextView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;
import com.example.fivemealsmobileproject.ui.login.SessionManager;
import com.example.fivemealsmobileproject.ui.main.TableInfo;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoriteAdapter.FavoriteEventListener {
    private Context context;
    private FavoriteAdapter adapter;
    private View view;
    private MainActivityNavBar mainActivityNavBar;

    public interface MainActivityNavBar{
        void hideNavBar();
        void showNavBar();
    }

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
                Navigation.findNavController(view).popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.context = container.getContext();
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        mainActivityNavBar.showNavBar();

        ImageView imageViewGoBack = view.findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewfavoriteFragment);
        List<FavoriteProduct> favoriteProducts = AppDataBase.getInstance(this.context).getFavoriteProductDAO().getAllFavorite(
                SessionManager.getActiveSession(context),
                TableInfo.getRestaurant().getRestaurantID()
        );
        this.adapter = new FavoriteAdapter(this,favoriteProducts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.context);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        checkIfIsEmpty();
    }

    @Override
    public void onFavoriteClick(long productID) {
        NavDirections action = (NavDirections) FavoritesFragmentDirections.actionFavoritesFragmentToHomeProductDetailsFragment(productID);
        Navigation.findNavController(this.view).navigate(action);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FavoritesFragment.MainActivityNavBar) this.mainActivityNavBar = (FavoritesFragment.MainActivityNavBar) context;
    }


    private void checkIfIsEmpty(){
        TextView empty = view.findViewById(R.id.textViewEmptyFavoritesMessage);
        if(this.adapter.getItemCount() == 0){
            empty.setVisibility(View.VISIBLE);
        }else {
            empty.setVisibility(View.GONE);
        }
    }
}