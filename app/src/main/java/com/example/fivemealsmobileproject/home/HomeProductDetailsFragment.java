package com.example.fivemealsmobileproject.home;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import com.example.fivemealsmobileproject.database.FavoriteProduct;
import com.example.fivemealsmobileproject.database.OrderProduct;
import com.example.fivemealsmobileproject.database.OrderProductDAO;
import com.example.fivemealsmobileproject.database.Product;
import com.example.fivemealsmobileproject.login.SessionManager;
import com.example.fivemealsmobileproject.main.MainActivity;
import com.example.fivemealsmobileproject.main.TableInfo;
import com.example.fivemealsmobileproject.order.ParentProductDB;

public class HomeProductDetailsFragment extends Fragment {

    private long productID;
    private MainActivityNavBar mainActivityNavBar;

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
        if (getArguments() != null) {
            this.productID = HomeProductDetailsFragmentArgs.fromBundle(getArguments()).getProductID();
            //this.mainActivityNavBar = HomeProductDetailsFragmentArgs.fromBundle(getArguments()).get

        }
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
        Product product = AppDataBase.getInstance(view.getContext()).getProductDAO().getById(this.productID);
        cacheViews(view);

        this.imageViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        String link = "https://docs.google.com/uc?id=" + product.getImgLink();
        imageViewProduct.setClipToOutline(true);
        Glide.with(view.getContext()).load(link).into(imageViewProduct);

        textViewTitle.setText(product.getName());
        textViewDescription.setText(product.getDescription());
        textViewPrice.setText(product.getPrice() + " $");

        int quantityToAdd = Integer.parseInt(textViewQuantity.getText().toString());
        buttonAddToOrder.setText(String.format("Add %d to cart %s $", quantityToAdd, quantityToAdd * product.getPrice()));

        buttonRemoveQuantity.setEnabled(false);
        //Add Quantity
        buttonAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString()) + 1;
                textViewQuantity.setText(String.valueOf(quantity));
                buttonRemoveQuantity.setEnabled(true);
                buttonAddToOrder.setText("Add " + quantity + " to cart " + quantity * product.getPrice() + " $");
            }
        });

        //Remove Quantity

        buttonRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString()) - 1;
                if (quantity == 1) buttonRemoveQuantity.setEnabled(false);
                textViewQuantity.setText(String.valueOf(quantity));
                buttonAddToOrder.setText(String.format("Add %s to cart %s $", quantity, quantity * product.getPrice()));
            }
        });


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

        buttonAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantityToAdd = Integer.parseInt(textViewQuantity.getText().toString());
                OrderProductDAO orderProductDAO = AppDataBase.
                        getInstance(getContext()).getOrderProductDAO();

                int state = forLater.isChecked() ? OrderProduct.WAITING_APPROVAL_STATE : OrderProduct.PENDING_STATE;

                for (int i = 1; i <= quantityToAdd; i++) {
                    orderProductDAO.insertOrderProduct(new OrderProduct(
                            productID,
                            SessionManager.getActiveSession(context),
                            TableInfo.getRestaurant().getRestaurantID(),
                            state,
                            System.currentTimeMillis()));
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