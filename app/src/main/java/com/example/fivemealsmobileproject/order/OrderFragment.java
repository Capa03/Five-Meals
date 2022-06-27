package com.example.fivemealsmobileproject.order;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.OrderProduct;
import com.example.fivemealsmobileproject.payment.PaymentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class OrderFragment extends Fragment implements CurrentOrderAdapter.ParentProductEventListener {

    private Context context;
    private CurrentOrderAdapter orderedProductsAdapter;
    private View view;
    private RefreshAdapter refreshAdapter;

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

        ImageView imageViewGoBack = view.findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        RecyclerView orderedRecyclerView = view.findViewById(R.id.recyclerViewOrderFragment);
        this.orderedProductsAdapter = new CurrentOrderAdapter(this);
        RecyclerView.LayoutManager orderedLayoutManager = new LinearLayoutManager(this.context);
        orderedRecyclerView.setAdapter(orderedProductsAdapter);
        orderedRecyclerView.setLayoutManager(orderedLayoutManager);

        refreshAdapter = new RefreshAdapter(this.orderedProductsAdapter);

        FloatingActionButton payButton = view.findViewById(R.id.FABOrderFragmentPayButton);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentActivity.startActivity(context);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        this.orderedProductsAdapter.updateData(ParentProductDB.getAll(context));
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                orderedProductsAdapter.notifyDataSetChanged();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
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