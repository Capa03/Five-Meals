package com.example.fivemealsmobileproject.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.Product;
import com.example.fivemealsmobileproject.login.PreLoginActivity;
import com.example.fivemealsmobileproject.login.SessionManager;

public class HomeProductDetailsFragment extends Fragment {

    private long productID;

    public HomeProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO getArguments().getProductID (Project/.gradle/build/generated/navigation-args/.../home)
        if(getArguments() != null) this.productID = HomeProductDetailsFragmentArgs.fromBundle(getArguments()).getProductID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Product product = AppDataBase.getInstance(view.getContext()).getProductDAO().getById(this.productID);

        ImageView imageViewProduct = view.findViewById(R.id.imageViewProductDetailsImage);
        imageViewProduct.setClipToOutline(true);
        TextView textViewTitle = view.findViewById(R.id.textViewProductDetailsTitle);
        ImageView goBackButton = view.findViewById(R.id.imageViewProductDetailsGoBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO guardar o estado do fragment
                NavDirections action = (NavDirections) HomeProductDetailsFragmentDirections.actionHomeProductDetailsFragmentToHomeFragment();
                Navigation.findNavController(view).navigate(action);            }
        });

        String link = "https://docs.google.com/uc?id=" + product.getImgLink();
        Glide.with(view.getContext()).load(link).into(imageViewProduct);
        textViewTitle.setText(product.getName());


        TextView textViewQuantity = view.findViewById(R.id.textViewProductDetailsQuantity);
        Button addQuantityButton = view.findViewById(R.id.buttonProductDetailsAddQuantity);
        Button removeQuantityButton = view.findViewById(R.id.buttonProductDetailsRemoveQuantity);
        removeQuantityButton.setEnabled(false);

        addQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString()) + 1;
                textViewQuantity.setText(String.valueOf(quantity));
                removeQuantityButton.setEnabled(true);
            }
        });

        removeQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString())-1;
                if (quantity == 1) removeQuantityButton.setEnabled(false);

                textViewQuantity.setText(String.valueOf(quantity));

            }
        });

    }

}