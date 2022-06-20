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
import com.example.fivemealsmobileproject.database.OrderProduct;


public class OrderFragment extends Fragment implements CurrentOrderAdapter.ParentProductEventListener {

    private Context context;
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

        RecyclerView orderedRecyclerView = view.findViewById(R.id.recyclerViewOrderFragment);
        this.orderedProductsAdapter = new CurrentOrderAdapter(this);
        RecyclerView.LayoutManager orderedLayoutManager = new LinearLayoutManager(this.context);
        orderedRecyclerView.setAdapter(orderedProductsAdapter);
        orderedRecyclerView.setLayoutManager(orderedLayoutManager);

    }

    @Override
    public void onStart() {
        super.onStart();
        this.orderedProductsAdapter.updateData(ParentProductDB.getAll(context));
    }

    @Override
    public void onRemoveProductClick(OrderProduct orderProduct) {
        // TODO Erro abre automaticamente os detalhes do item de baixo quando o de cima é eliminado
        // ou fecha todos
        AppDataBase.getInstance(context).getOrderProductDAO().deleteOrderProduct(orderProduct);
        ParentProductDB.removeProduct(context, orderProduct.getProductID());
        this.orderedProductsAdapter.updateData(ParentProductDB.getAll(context));
    }
}