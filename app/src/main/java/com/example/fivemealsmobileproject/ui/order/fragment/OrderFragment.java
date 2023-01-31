package com.example.fivemealsmobileproject.ui.order.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.ui.order.ParentOrderProduct;
import com.example.fivemealsmobileproject.ui.order.adapter.CurrentOrderAdapter;
import com.example.fivemealsmobileproject.ui.order.ParentProductDB;
import com.example.fivemealsmobileproject.ui.order.viewmodel.OrderFragmentViewModel;
import com.example.fivemealsmobileproject.ui.payment.PaymentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class OrderFragment extends Fragment {

    private CurrentOrderAdapter orderedProductsAdapter;
    private View view;
    private OrderFragmentViewModel viewModel;

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
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(requireActivity()).get(OrderFragmentViewModel.class);

        this.view = view;
        ImageView imageViewGoBack = view.findViewById(R.id.imageViewToolBarGoBack);
        imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });


        this.orderedProductsAdapter = new CurrentOrderAdapter(requireActivity(), new CurrentOrderAdapter.ParentProductEventListener() {
            @Override
            public void onRemoveProductClick(OrderProduct orderProduct, int position) {
                AppDataBase.getInstance(view.getContext()).getOrderProductDAO().deleteOrderProduct(orderProduct);
                checkIfIsEmpty();
            }
        });
        RecyclerView orderedRecyclerView = view.findViewById(R.id.recyclerViewOrderFragment);
        orderedRecyclerView.setAdapter(orderedProductsAdapter);
        orderedRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        view.findViewById(R.id.FABOrderFragmentPayButton).setOnClickListener(
                v -> PaymentActivity.startActivity(view.getContext())
        );

        this.viewModel.getParentOrderProducts(requireActivity()).observe(requireActivity(), parentOrderProducts -> {
            orderedProductsAdapter.updateData(parentOrderProducts);
            checkIfIsEmpty();
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // "Refresh"
        /*
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                orderedProductsAdapter.notifyDataSetChanged();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
        */
        this.viewModel.fetchData();
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