package com.example.fivemealsmobileproject.login;

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

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.User;
import com.example.fivemealsmobileproject.qrcode.CodeActivity;

public class LoginActivity extends AppCompatActivity {

    // simular correcção issue Test

    private EditText editTextLoginUsername;
    private EditText editTextLoginPassword;
    private TextView showHidePassword;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.cacheViews();

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

        String username = this.editTextLoginUsername.getText().toString();
        int password = this.editTextLoginPassword.getText().toString().hashCode();

        User loggedInUser = LoginManager.validateUser(this, username, password);

        if (loggedInUser != null) {
            // user válido
            SessionManager.saveSession(this, username, false);
            Toast.makeText(this, "Successful Login", Toast.LENGTH_LONG).show();

            CodeActivity.startActivity(this);
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show();
        }

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
        this.editTextLoginUsername = findViewById(R.id.editTextLoginUsername);
        this.editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        this.showHidePassword = findViewById(R.id.textViewLoginPasswordShowOrHide1);
    }

}