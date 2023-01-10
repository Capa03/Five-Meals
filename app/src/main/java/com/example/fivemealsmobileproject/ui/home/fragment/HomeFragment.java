package com.example.fivemealsmobileproject.ui.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.ui.home.viewmodel.HomeFragmentViewModel;
import com.example.fivemealsmobileproject.ui.home.adapter.CategoryListAdapter;

import java.util.List;


public class HomeFragment extends Fragment {

    public static int FIRST_ITEM = 1234;

    private MainActivityNavBar mainActivityNavBar;
    private HomeFragmentViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("test");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityNavBar) this.mainActivityNavBar = (MainActivityNavBar) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private CategoryListAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(requireActivity()).get(HomeFragmentViewModel.class);

        // Hide go back button
        view.findViewById(R.id.imageViewToolBarGoBack).setVisibility(View.GONE);

        adapter = new CategoryListAdapter(requireActivity(), new CategoryListAdapter.CategoryListEventListener() {
            @Override
            public void onProductClick(long productId) {
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToHomeProductDetailsFragment(productId);
                Navigation.findNavController(view).navigate(action);
            }

            @Override
            public LiveData<List<Product>> getProducts(String category) {
                return viewModel.getProducts(category);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHomeFragmentCategoryList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mainActivityNavBar.showNavBar();


        // TODO rever lifecycle e fazer com que o código rode apenas uma vez
        this.viewModel.getCategories().observe(requireActivity(), categories -> {
            if (adapter != null) adapter.updateData(categories);
        });
    }

    public interface MainActivityNavBar{
        void hideNavBar();
        void showNavBar();
    }
}