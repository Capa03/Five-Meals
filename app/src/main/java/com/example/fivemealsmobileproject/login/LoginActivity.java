package com.example.fivemealsmobileproject.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLoginUsername;
    private EditText editTextLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.cacheViews();

        if(SessionManager.persistedSession(this)){

            finish();
        }
    }

    public void onSignIn(View view) {

        String username = this.editTextLoginUsername.getText().toString();
        String password = this.editTextLoginPassword.getText().toString();

        List<User> loggedInUser = AppDataBase.getInstance(this).getUserDAO().getAllUser();

        for (User user : loggedInUser) {

            if (username.isEmpty()) {

                editTextLoginUsername.setError("Username cannot be empty!");
                return;
            }

            if (password.isEmpty()) {
                editTextLoginPassword.setError("Password cannot be empty!");
                return;
            }

            Log.i("LoginActivityPW", String.valueOf(password.hashCode()));
/*
            if (loggedInUser != null) {
                // user válido
                SessionManager.saveSession(this, loggedInUser.get((int) user.getUserId()).getUsername(), checkBoxRemeberMe.isChecked());

                Toast.makeText(this, "Login com sucesso", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_LONG).show();
            }
*/
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
    }

}