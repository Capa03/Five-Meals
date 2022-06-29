package com.example.fivemealsmobileproject.home;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.Category;

import java.util.List;


public class HomeFragment extends Fragment implements CategoryListAdapter.CategoryListEventListener{

    private Context context;
    private CategoryListAdapter adapter;
    private View view;
    private MainActivityNavBar mainActivityNavBar;
    ViewGroup container;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = container.getContext();
        this.container = container;
        // TODO create ActionBar
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        ImageView imageViewGoBack = view.findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setVisibility(View.GONE);

        List<Category> categories = AppDataBase.getInstance(this.context).getCategoryDAO().getAllCategories();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHomeFragmentCategoryList);
        this.adapter = new CategoryListAdapter(this, categories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        mainActivityNavBar.showNavBar();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityNavBar) this.mainActivityNavBar = (MainActivityNavBar) context;
    }

    @Override
    public void onCategoryClick(String categoryName) {
        Log.i("ListenerDEBUG", ("Category: " + categoryName));
    }

    @Override
    public void onProductClick(long productID) {
        NavDirections action = (NavDirections) HomeFragmentDirections.actionHomeFragmentToHomeProductDetailsFragment(productID);
        Navigation.findNavController(this.view).navigate(action);
    }


    public interface MainActivityNavBar{
        void hideNavBar();
        void showNavBar();
    }
}