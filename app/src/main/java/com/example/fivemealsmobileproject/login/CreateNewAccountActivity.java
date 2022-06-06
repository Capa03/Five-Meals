package com.example.fivemealsmobileproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;

public class CreateNewAccountActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,CreateNewAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        cacheViews();

    }

    public void onSignUp(View view) {
        boolean somethingEmpty = false;
        String username = this.username.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();


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
        if(!somethingEmpty){
            // TODO aplicação rebenta quando já exite um user igual
            User user =  new User(username,email,password.hashCode());
            password = "0";

            AppDataBase.getInstance(this).getUserDAO().insert(user);
            finish();
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
    }


}