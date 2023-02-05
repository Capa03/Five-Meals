package com.example.fivemealsmobileproject.ui.favorites.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.ui.favorites.adapter.FavoriteAdapter;
import com.example.fivemealsmobileproject.ui.favorites.viewmodel.FavoritesFragmentViewModel;

public class FavoritesFragment extends Fragment {
    private FavoriteAdapter adapter;
    private View view;
    private MainActivityNavBar mainActivityNavBar;
    private FavoritesFragmentViewModel viewModel;

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
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        this.viewModel = new ViewModelProvider(requireActivity()).get(FavoritesFragmentViewModel.class);
        this.viewModel.initializeRepository(requireActivity());
        mainActivityNavBar.showNavBar();

        view.findViewById(R.id.imageViewToolBarGoBack).setOnClickListener(
                goBackView -> Navigation.findNavController(goBackView).popBackStack());


        this.adapter = new FavoriteAdapter(favoriteId -> {
            NavDirections action = FavoritesFragmentDirections.actionFavoritesFragmentToHomeProductDetailsFragment(favoriteId);
            Navigation.findNavController(view).navigate(action);
        });

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewfavoriteFragment);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        this.viewModel.getFavoritesLiveData().observe(requireActivity(), favoriteProducts -> {
            adapter.updateData(favoriteProducts);
            checkIfIsEmpty();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FavoritesFragment.MainActivityNavBar) {
            this.mainActivityNavBar = (FavoritesFragment.MainActivityNavBar) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.viewModel.refreshData();
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