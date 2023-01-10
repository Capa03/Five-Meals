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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.User;
import com.example.fivemealsmobileproject.domain.models.UserCreateDTO;
import com.example.fivemealsmobileproject.ui.login.SessionManager;
import com.example.fivemealsmobileproject.ui.login.viewmodels.ViewModelCreateAccount;
import com.example.fivemealsmobileproject.ui.qrcode.CodeActivity;

public class CreateNewAccountActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private TextView showHidePassword;
    private TextView showHideConfirmPassword;

    private ViewModelCreateAccount viewModel;
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CreateNewAccountActivity.class);
        context.startActivity(intent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        cacheViews();

        this.viewModel = new ViewModelProvider(this).get(ViewModelCreateAccount.class);

        this.showHidePassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });

        this.showHideConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });
    }

    public void onSignUp(View view) {
        boolean somethingEmpty = false;
        String username = this.username.getText().toString();
        String email = this.email.getText().toString();
        int password = this.password.getText().toString().hashCode();
        int confirmPassword = this.confirmPassword.getText().toString().hashCode();

        if (username.isEmpty()) {
            this.username.setError("Empty Username");
            somethingEmpty = true;
        }
        if (email.isEmpty()) {
            this.email.setError("Empty Email");
            somethingEmpty = true;
        }
        if (password == 0) {
            this.password.setError("Empty Password");
            somethingEmpty = true;
        }
        if (confirmPassword == 0) {
            this.confirmPassword.setError("Password empty");
            somethingEmpty = true;
        }

        if (confirmPassword != password) {
            this.confirmPassword.setError("Password does not match");
            somethingEmpty = true;
        }

        if (!somethingEmpty) {
            UserCreateDTO user = new UserCreateDTO(username,email,String.valueOf(password));
            if(this.viewModel.createUser(user)){
                CodeActivity.startActivity(this);
                finish();
            }
        }
    }

    public void onHaveAccountClicked(View view) {
        LoginActivity.startActivity(this);
        finish();
    }

    private void cacheViews() {
        this.username = findViewById(R.id.editTextCreateAccountUsername);
        this.email = findViewById(R.id.editTextCreateAccountEmail);
        this.password = findViewById(R.id.editTextCreateAccountPassword);
        this.confirmPassword = findViewById(R.id.editTextCreateAccountConfirmPassword);
        this.showHidePassword = findViewById(R.id.textViewCreateAccountPasswordShowOrHide1);
        this.showHideConfirmPassword = findViewById(R.id.textViewCreateAccountPasswordShowOrHide2);
    }
}