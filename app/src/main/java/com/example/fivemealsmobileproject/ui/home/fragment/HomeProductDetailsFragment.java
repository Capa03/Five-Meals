package com.example.fivemealsmobileproject.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.FavoriteProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.ui.home.viewmodel.HomeProductDetailsFragmentViewModel;
import com.example.fivemealsmobileproject.ui.login.SessionManager;
import com.example.fivemealsmobileproject.ui.main.TableInfo;
import com.example.fivemealsmobileproject.ui.order.ParentProductDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class HomeProductDetailsFragment extends Fragment {

    private long productID;
    private MainActivityNavBar mainActivityNavBar;
    private HomeProductDetailsFragmentViewModel viewModel;

    private ImageView imageViewProduct;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewPrice;



    private TextView textViewQuantity;
    private Button buttonAddQuantity;
    private Button buttonRemoveQuantity;
    private CheckBox forLater;
    private Button buttonAddToOrder;
    private ImageView imageViewGoBack;
    private Context context;
    private ImageView favorite;
    private boolean favoriteOn = true;
    private View view;

    public HomeProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.productID = HomeProductDetailsFragmentArgs.fromBundle(getArguments()).getProductId();
        this.viewModel = new ViewModelProvider(requireActivity()).get(HomeProductDetailsFragmentViewModel.class);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = container.getContext();
        return inflater.inflate(R.layout.fragment_home_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        cacheViews(view);

        this.imageViewGoBack.setOnClickListener(imageViewGoBackView -> Navigation.findNavController(imageViewGoBackView).popBackStack());

        this.viewModel.getProduct(this.productID).observe(requireActivity(), product -> {
            textViewTitle.setText(product.getName());
            textViewDescription.setText(product.getDescription());
            textViewPrice.setText(String.format("%s $", product.getPrice()));
            imageViewProduct.setClipToOutline(true);

            // TODO remover o prefixo do link
            Glide.with(view.getContext()).load("https://docs.google.com/uc?id=" + product.getImgLink()).into(imageViewProduct);

            viewModel.resetQuantity();
            viewModel.getQuantity().observe(requireActivity(), quantity -> {
                buttonRemoveQuantity.setEnabled(quantity > 1);

                // Add to order button text
                buttonAddToOrder.setText(String.format(Locale.getDefault(),
                        "Add %d to cart : %s $",
                        quantity, quantity * product.getPrice()));
                textViewQuantity.setText(String.valueOf(quantity));

                // Add Quantity
                buttonAddQuantity.setOnClickListener(buttonAddQuantityView -> viewModel.incrementQuantity());

                // Remove Quantity
                buttonRemoveQuantity.setOnClickListener(buttonRemoveQuantityView -> viewModel.decrementQuantity());

            });

        });

        buttonAddToOrder.setOnClickListener(buttonAddToOrderView -> {
            // forLater.isChecked();
            viewModel.addProducts(false);
            Navigation.findNavController(view).popBackStack();
        });
        mainActivityNavBar.hideNavBar();



        /*
        FavoriteProduct exist = AppDataBase.getInstance(this.context).getFavoriteProductDAO().getFromId(
                productID,
                SessionManager.getActiveSession(context),
                product.getRestaurantId()
                );
        if (exist == null) {
            favoriteOn = false;
            favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }else {
            favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteProduct favoriteProduct = new FavoriteProduct(
                        productID,
                        SessionManager.getActiveSession(context),
                        product.getRestaurantId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImgLink());
                if (!favoriteOn) {
                    favoriteOn = true;
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    AppDataBase.getInstance(context).getFavoriteProductDAO().insertFavorite(favoriteProduct);

                } else {
                    favoriteOn = false;
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    AppDataBase.getInstance(context).getFavoriteProductDAO().deleteFavorite(favoriteProduct);
                }
            }
        });
    */
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityNavBar)
            this.mainActivityNavBar = (MainActivityNavBar) context;
    }

    public interface MainActivityNavBar {
        void hideNavBar();
        void showNavBar();
    }

    private void cacheViews(View view) {
        this.imageViewProduct = view.findViewById(R.id.imageViewProductDetailsImage);
        this.textViewTitle = view.findViewById(R.id.textViewProductDetailsTitle);
        this.textViewDescription = view.findViewById(R.id.textViewProductDetailsDescription);
        this.textViewPrice = view.findViewById(R.id.textViewProductDetailsPrice);
        this.textViewQuantity = view.findViewById(R.id.textViewProductDetailsQuantity);
        this.buttonAddQuantity = view.findViewById(R.id.buttonProductDetailsAddQuantity);
        this.buttonRemoveQuantity = view.findViewById(R.id.buttonProductDetailsRemoveQuantity);
        this.forLater = view.findViewById(R.id.checkBoxProductDetailsOrderLater);
        this.buttonAddToOrder = view.findViewById(R.id.buttonProductDetailsAddToOrder);
        this.imageViewGoBack = view.findViewById(R.id.imageViewToolBarGoBack);
        this.favorite = view.findViewById(R.id.toggleButtonProductDetailsFavorite);
    }

}