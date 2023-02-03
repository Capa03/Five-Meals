package com.example.fivemealsmobileproject.ui.login.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.models.auth.GetTokenRequest;
import com.example.fivemealsmobileproject.ui.login.viewmodels.ViewModelLogin;
import com.example.fivemealsmobileproject.ui.qrcode.view.CodeActivity;

public class LoginActivity extends AppCompatActivity {

    // simular correcção issue Test

    private EditText editTextLoginEmail;
    private EditText editTextLoginPassword;
    private TextView showHidePassword;
    private ViewModelLogin viewModel;
    private Context context;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.cacheViews();
        this.context = this;

        this.viewModel = new ViewModelProvider(this).get(ViewModelLogin.class);

        this.showHidePassword.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        editTextLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        editTextLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });


    }

    public void onSignIn(View view) {

        String email = this.editTextLoginEmail.getText().toString();
        String password = this.editTextLoginPassword.getText().toString();
        GetTokenRequest getTokenRequest = new GetTokenRequest(email, password);
        this.viewModel.getToken(getTokenRequest).observe(this, tokenSuccess -> {
            if(tokenSuccess){
                CodeActivity.startActivity(context);
                finish();
            }
        });
    }

    public void onDontHaveAccountClicked(View view) {
       CreateNewAccountActivity.startActivity(this);
       finish();
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }


    private void cacheViews(){
        this.editTextLoginEmail = findViewById(R.id.editTextLoginEmail);
        this.editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        this.showHidePassword = findViewById(R.id.textViewLoginPasswordShowOrHide1);
    }

}