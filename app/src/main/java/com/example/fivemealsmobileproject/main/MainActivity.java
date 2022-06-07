package com.example.fivemealsmobileproject.main;

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
import com.example.fivemealsmobileproject.login.PreLoginActivity;
import com.example.fivemealsmobileproject.login.SessionManager;

public class MainActivity extends AppCompatActivity {

    private static String KEY_CODE = "getCode";



    public static void startActivity(Context context, long code) {
        Intent intent = new Intent(context, com.example.fivemealsmobileproject.main.MainActivity.class);
        intent.putExtra(KEY_CODE, code);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationController();
        // TODO Fragments
        // TODO utilizar os extras para saber a mesa
        // TODO Room para as mesas e restaurantes
    }

    public void onLogOutClick(View view) {
        SessionManager.clearSession(this);
        PreLoginActivity.startActivity(this);
        finish();
    }

    private void navigationController(){
        ActivityMainBinding binding;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        NavHostFragment navHostFragment = (NavHostFragment) fragment;

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());
    }
}