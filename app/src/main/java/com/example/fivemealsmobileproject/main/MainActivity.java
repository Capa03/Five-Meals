package com.example.fivemealsmobileproject.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.databinding.ActivityMainBinding;
import com.example.fivemealsmobileproject.home.HomeFragment;
import com.example.fivemealsmobileproject.home.HomeProductDetailsFragment;
import com.example.fivemealsmobileproject.login.PreLoginActivity;
import com.example.fivemealsmobileproject.login.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements HomeProductDetailsFragment.MainActivityNavBar, HomeFragment.MainActivityNavBar {

    private static String KEY_CODE = "getCode";
    ActivityMainBinding binding;

    public static void startActivity(Context context, long code) {
        Intent intent = new Intent(context, com.example.fivemealsmobileproject.main.MainActivity.class);
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

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // TODO utilizar os extras para saber a mesa
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