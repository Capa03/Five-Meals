package com.example.fivemealsmobileproject.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.OrderProduct;
import com.example.fivemealsmobileproject.database.OrderProductDAO;
import com.example.fivemealsmobileproject.database.Product;
import com.example.fivemealsmobileproject.order.ParentProductDB;

public class HomeProductDetailsFragment extends Fragment {

    private long productID;
    private MainActivityNavBar mainActivityNavBar;

    public HomeProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            this.productID = HomeProductDetailsFragmentArgs.fromBundle(getArguments()).getProductID();
            //this.mainActivityNavBar = HomeProductDetailsFragmentArgs.fromBundle(getArguments()).get
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Product product = AppDataBase.getInstance(view.getContext()).getProductDAO().getById(this.productID);

        ImageView buttonGoBack = view.findViewById(R.id.imageViewProductDetailsGoBackButton);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        String link = "https://docs.google.com/uc?id=" + product.getImgLink();
        ImageView imageViewProduct = view.findViewById(R.id.imageViewProductDetailsImage);
        imageViewProduct.setClipToOutline(true);

        Glide.with(view.getContext()).load(link).into(imageViewProduct);
        TextView textViewTitle = view.findViewById(R.id.textViewProductDetailsTitle);
        textViewTitle.setText(product.getName());

        // TODO cacheviews or not????
        TextView textViewQuantity = view.findViewById(R.id.textViewProductDetailsQuantity);
        Button buttonAddQuantity = view.findViewById(R.id.buttonProductDetailsAddQuantity);
        Button buttonRemoveQuantity = view.findViewById(R.id.buttonProductDetailsRemoveQuantity);
        buttonRemoveQuantity.setEnabled(false);

        buttonAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString()) + 1;
                textViewQuantity.setText(String.valueOf(quantity));
                buttonRemoveQuantity.setEnabled(true);
            }
        });

        buttonRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString())-1;
                if (quantity == 1) buttonRemoveQuantity.setEnabled(false);
                textViewQuantity.setText(String.valueOf(quantity));

            }
        });

        CheckBox forLater = view.findViewById(R.id.checkBoxProductDetailsOrderLater);
        Button buttonAddToOrder = view.findViewById(R.id.buttonProductDetailsAddToOrder);
        buttonAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantityToAdd = Integer.parseInt(textViewQuantity.getText().toString());
                OrderProductDAO orderProductDAO = AppDataBase.
                        getInstance(getContext()).getOrderProductDAO();

                int state = forLater.isChecked() ? OrderProduct.WAITING_APPROVAL_STATE : OrderProduct.PENDING_STATE;

                for(int i = 1; i<=quantityToAdd; i++){
                    orderProductDAO.insertOrderProduct(new OrderProduct(productID, state, System.currentTimeMillis()));
                    ParentProductDB.addProduct(productID);
                }
                Navigation.findNavController(view).popBackStack();
            }
        });
        mainActivityNavBar.hideNavBar();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivityNavBar) this.mainActivityNavBar = (MainActivityNavBar) context;
    }

    public interface MainActivityNavBar{
        void hideNavBar();
        void showNavBar();
    }

}