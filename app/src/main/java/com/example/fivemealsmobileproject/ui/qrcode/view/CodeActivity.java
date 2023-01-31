package com.example.fivemealsmobileproject.ui.qrcode.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.ui.login.viewmodels.ViewModelCreateAccount;
import com.example.fivemealsmobileproject.ui.main.MainActivity;
import com.example.fivemealsmobileproject.ui.qrcode.viewmodels.CodeActivityViewModel;

public class CodeActivity extends AppCompatActivity {

    private EditText codeInput;
    private CodeActivityViewModel viewModel;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_input);
        cacheViews();
        Context context = this;
        LifecycleOwner lifecycleOwner = this;
        this.viewModel = new ViewModelProvider(this).get(CodeActivityViewModel.class);
        this.viewModel.initializeRepository(this);


        // TODO Debug Button
        Button debugButton = findViewById(R.id.buttonToMainActivity);
        debugButton.setVisibility(View.GONE);

        this.codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 8){
                    viewModel.getTableFromId(Integer.parseInt(s.toString())).observe(lifecycleOwner, success -> {
                        if(success){
                            viewModel.getOrderFromTable();
                            MainActivity.startActivity(context);
                        }else {
                            Toast.makeText(context, "Invalid code", Toast.LENGTH_SHORT).show();
                        }
                        codeInput.setText("");
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onQrCodeScanClick(View view) {
        QrCodeActivity.startActivity(this);
        this.codeInput.setText("");
    }

    private void cacheViews(){
        this.codeInput = findViewById(R.id.editTextCodeInput);

    }

    public void onDebug(View view) {
        MainActivity.startActivity(this);
    }
}