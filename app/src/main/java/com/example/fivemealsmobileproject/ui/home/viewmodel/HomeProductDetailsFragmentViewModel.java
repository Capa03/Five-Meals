package com.example.fivemealsmobileproject.ui.home.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.datasource.room.OrderProductDAO;
import com.example.fivemealsmobileproject.datasource.room.Product;
import com.example.fivemealsmobileproject.datasource.room.ProductDAO;
import com.example.fivemealsmobileproject.ui.login.SessionManager;
import com.example.fivemealsmobileproject.ui.main.TableInfo;

public class HomeProductDetailsFragmentViewModel extends AndroidViewModel {
    private final ProductDAO productDAO;
    private final OrderProductDAO orderProductDAO;
    private MutableLiveData<Integer> quantityLiveData;
    private Context context;
    private int quantity;
    private long productID;

    public HomeProductDetailsFragmentViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.productDAO = AppDataBase.getInstance(application).getProductDAO();
        this.orderProductDAO = AppDataBase.getInstance(application).getOrderProductDAO();
        this.quantityLiveData = new MutableLiveData<>();
    }

    public LiveData<Product> getProduct(long productId){
        this.productID = productId;
        return this.productDAO.getLiveDataById(productId);
    }

    public LiveData<Integer> getQuantity() { return this.quantityLiveData; }

    public void incrementQuantity(){ this.quantityLiveData.postValue(++quantity); }

    public void decrementQuantity(){ this.quantityLiveData.postValue(--quantity); }

    public void resetQuantity(){
        this.quantity = 1;
        this.quantityLiveData.postValue(quantity);
    }
    public void addProducts(boolean forLater){
        int state = forLater ? OrderProduct.WAITING_APPROVAL_STATE : OrderProduct.PENDING_STATE;
        // Erro null com : this.productDAO.getLiveDataById(productID).getValue()
        Product product = this.productDAO.getById(productID);
        for (int i = 1; i <= quantity; i++) {
            orderProductDAO.insertOrderProduct(new OrderProduct(
                    SessionManager.getActiveSession(context),
                    TableInfo.getTable().getTableID(),
                    state,
                    System.currentTimeMillis(),
                    productID,
                    product.getName(),
                    product.getPrice(),
                    product.getMinAverageTime(),
                    product.getMaxAverageTime(),
                    product.getImgLink())
            );
        }
    }
}
