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

        User loggedInUser = LoginManager.validateUser(username,password,this);


            String hash = String.valueOf(password.hashCode());

            Log.i("LoginActivityPW", hash);
            Toast.makeText(this,hash,Toast.LENGTH_SHORT).show();

        if (loggedInUser != null) {
            // user válido
            SessionManager.saveSession(this, username, checkBoxRemeberMe.isChecked());
            Toast.makeText(this, "Login com sucesso", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_LONG).show();
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