package com.example.fivemealsmobileproject.login;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;
import com.example.fivemealsmobileproject.qrcode.CodeActivity;

public class CreateNewAccountActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private TextView showHidePassword;
    private TextView showHideConfirmPassword;
    public static void startActivity(Context context) {
        Intent intent = new Intent(context,CreateNewAccountActivity.class);
        context.startActivity(intent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        cacheViews();

        this.showHidePassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

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
                switch ( event.getAction() ) {

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
        String username = this.username.getText().toString(); // TODO onTextChanged Warning
        String email = this.email.getText().toString(); // TODO onTextChanged Warning
        String password = this.password.getText().toString(); // TODO onTextChanged Warning
        String confirmPassword = this.confirmPassword.getText().toString();

        if(username.isEmpty()){
            this.username.setError("Empty Username");
            somethingEmpty = true;
        }
        if(email.isEmpty()){
            this.email.setError("Empty Email");
            somethingEmpty = true;
        }
        if(password.isEmpty()){
            this.password.setError("Empty Password");
            somethingEmpty = true;
        }
        if(confirmPassword.isEmpty() || !confirmPassword.equals(password)){
            this.confirmPassword.setError("Password not match");
            somethingEmpty = true;
        }

        if(!somethingEmpty){
            if(!LoginManager.userExists(this, username)){
                if(!LoginManager.emailExists(this, email)){
                    User user =  new User(username,email,password.hashCode());
                    password = "0";

                    SessionManager.saveSession(this, username, false);
                    AppDataBase.getInstance(this).getUserDAO().insert(user);
                    CodeActivity.startActivity(this);
                    finish();
                }else Toast.makeText(this, "Email already being used", Toast.LENGTH_SHORT).show();

            }else Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();

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