package com.example.fivemealsmobileproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;
import com.example.fivemealsmobileproject.qrcode.CodeActivity;

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
        String username = this.username.getText().toString(); // TODO onTextChanged Warning
        String email = this.email.getText().toString(); // TODO onTextChanged Warning
        int password = this.password.getText().toString().hashCode(); // TODO onTextChanged Warning


        if(username.isEmpty()){
            this.username.setError("Empty Username");
            somethingEmpty = true;
        }
        if(email.isEmpty()){
            this.email.setError("Empty Email");
            somethingEmpty = true;
        }
        if(password == 0){
            this.password.setError("Empty Password");
            somethingEmpty = true;
        }
        if(!somethingEmpty){
            if(!LoginManager.userExists(this, username)){
                if(!LoginManager.emailExists(this, email)){
                    User user =  new User(username,email,password);

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
    }


}