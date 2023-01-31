package com.example.fivemealsmobileproject.datasource.repository.product;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.fivemealsmobileproject.datasource.models.CategoryWithProducts;
import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.datasource.remote.ProductsService;
import com.example.fivemealsmobileproject.datasource.repository.localization.LocalizationRepository;
import com.example.fivemealsmobileproject.datasource.repository.auth.AuthRepository;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.Category;
import com.example.fivemealsmobileproject.datasource.room.CategoryDAO;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.datasource.room.ProductDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsRepository {

    private final String CAPA = "http://10.0.2.2:5168/";
    private final String LUIS = "http://192.168.1.70:5168";

    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private final Activity activity;
    private final ProductsService productsService;
    private LocalizationRepository localizationRepository;
    private AuthRepository authRepository;

    public ProductsRepository(Activity activity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LUIS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.categoryDAO = AppDataBase.getInstance(activity).getCategoryDAO();
        this.productDAO = AppDataBase.getInstance(activity).getProductDAO();

        this.authRepository = new AuthRepository(activity);
        this.localizationRepository = new LocalizationRepository(activity);
        this.productsService = retrofit.create(ProductsService.class);
        this.activity = activity;
    }

    public void refreshDataSet(){
        this.productsService.getCategoriesWithProductsByRestaurantId(
                this.localizationRepository.getSavedRestaurantId(),
                this.authRepository.getSavedToken()).enqueue(new Callback<List<CategoryWithProducts>>() {
            @Override
            public void onResponse(Call<List<CategoryWithProducts>> call, Response<List<CategoryWithProducts>> response) {
                if(response.code() == 401){
                    Toast.makeText(activity, "Fetching new token", Toast.LENGTH_LONG).show();
                    GetTokenRequest getTokenRequest = new GetTokenRequest(
                            authRepository.getSavedEmail(),
                            authRepository.getSavedPasswordHash());
                    authRepository.fetchToken(getTokenRequest).observe((LifecycleOwner) activity, tokenSuccess -> {
                        if(tokenSuccess){
                            refreshDataSet();
                        }
                    });
                }else if(response.isSuccessful()){
                    for (CategoryWithProducts categoryWithProducts : response.body()) {
                        productDAO.insertProducts(categoryWithProducts.getProducts());
                        categoryDAO.insertCategory(new Category(
                                categoryWithProducts.getId(),
                                categoryWithProducts.getRestaurantId(),
                                categoryWithProducts.getCategoryName()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryWithProducts>> call, Throwable t) {
                t.getStackTrace();
                //TODO Make strings
                Toast.makeText(activity, "There was a connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public LiveData<List<Category>> getCategories(){
        return this.categoryDAO.getAllCategoriesFromRestaurant(this.localizationRepository.getSavedRestaurantId());
    }

    public LiveData<List<Product>> getProducts(String category){
        return this.productDAO.getAllFromCategoryAndRestaurant(category, this.localizationRepository.getSavedRestaurantId());
    }

    public LiveData<Product> getProductById(long id){
        return this.productDAO.getLiveDataById(id);
    }
}
