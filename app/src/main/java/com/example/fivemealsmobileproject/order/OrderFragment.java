package com.example.fivemealsmobileproject.order;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;


public class OrderFragment extends Fragment {

    private Context context;
    private CurrentOrderAdapter laterProductsAdapter;
    private CurrentOrderAdapter orderedProductsAdapter;
    private View view;

    public OrderFragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        RecyclerView laterRecyclerView = view.findViewById(R.id.recyclerViewOrderForLater);
        this.laterProductsAdapter = new CurrentOrderAdapter();
        RecyclerView.LayoutManager laterLayoutManager = new LinearLayoutManager(this.context);
        laterRecyclerView.setAdapter(laterProductsAdapter);
        laterRecyclerView.setLayoutManager(laterLayoutManager);

        RecyclerView orderedRecyclerView = view.findViewById(R.id.recyclerViewOrderOrdered);
        this.orderedProductsAdapter = new CurrentOrderAdapter();
        RecyclerView.LayoutManager orderedLayoutManager = new LinearLayoutManager(this.context);
        orderedRecyclerView.setAdapter(orderedProductsAdapter);
        orderedRecyclerView.setLayoutManager(orderedLayoutManager);


    }

    @Override
    public void onStart() {
        super.onStart();
        this.orderedProductsAdapter.updateData(AppDataBase.getInstance(this.context).getProductWithQuantityDAO().getAllOrderedProducts());
        this.laterProductsAdapter.updateData(AppDataBase.getInstance(this.context).getProductWithQuantityDAO().getAllLaterProducts());
    }
}