package com.example.fivemealsmobileproject.ui.order;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.ui.payment.PaymentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


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
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Navigation.findNavController(view).popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

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

        checkIfIsEmpty();
    }


    @Override
    public void onRemoveProductClick(OrderProduct orderProduct) {
        AppDataBase.getInstance(context).getOrderProductDAO().deleteOrderProduct(orderProduct);
        ParentProductDB.removeProduct(context, orderProduct.getProductID());
        this.orderedProductsAdapter.updateData(ParentProductDB.getAll(context));
        checkIfIsEmpty();
    }

    private void checkIfIsEmpty(){
        TextView empty = view.findViewById(R.id.textViewEmptyOrderMessage);
        if(this.orderedProductsAdapter.getItemCount() == 0){
            empty.setVisibility(View.VISIBLE);
        }else {
            empty.setVisibility(View.GONE);
        }
    }
}