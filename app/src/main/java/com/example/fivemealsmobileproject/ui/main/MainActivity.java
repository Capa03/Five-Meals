package com.example.fivemealsmobileproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.Table;
import com.example.fivemealsmobileproject.databinding.ActivityMainBinding;
import com.example.fivemealsmobileproject.ui.favorites.FavoritesFragment;
import com.example.fivemealsmobileproject.ui.home.HomeFragment;
import com.example.fivemealsmobileproject.ui.home.HomeProductDetailsFragment;
import com.example.fivemealsmobileproject.ui.login.PreLoginActivity;
import com.example.fivemealsmobileproject.ui.login.SessionManager;

public class MainActivity extends AppCompatActivity implements HomeFragment.MainActivityNavBar, HomeProductDetailsFragment.MainActivityNavBar, FavoritesFragment.MainActivityNavBar {

    private static String KEY_CODE = "getCode";
    public static int FIRST_ITEM = 1234;
    ActivityMainBinding binding;

    public static void startActivity(Context context, long code) {
        Intent intent = new Intent(context, com.example.fivemealsmobileproject.ui.main.MainActivity.class);
        intent.putExtra(KEY_CODE, code);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView.getId());
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }


        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Table table = AppDataBase.getInstance(this).getTableDAO().getTableFromID(bundle.getLong(KEY_CODE));
            TableInfo.setTable(table);
            TableInfo.setRestaurant(AppDataBase.getInstance(this)
                    .getRestaurantDAO().getRestaurantFromID(table.getRestaurantID()));
        }
    }

    public void onLogOutClick(View view) {
        SessionManager.clearSession(this);
        PreLoginActivity.startActivity(this);
        finish();
    }


    @Override
    public void hideNavBar() {
        binding.bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void showNavBar() {
        binding.bottomNavigationView.setVisibility(View.VISIBLE);
    }
}