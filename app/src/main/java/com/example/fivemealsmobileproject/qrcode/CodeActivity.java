package com.example.fivemealsmobileproject.qrcode;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.main.MainActivity;
import com.example.fivemealsmobileproject.order.ParentProductDB;

public class CodeActivity extends AppCompatActivity {

    private EditText codeInput;

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

        // TODO Debug Button
        Button debugButton = findViewById(R.id.buttonToMainActivity);
        debugButton.setVisibility(View.GONE);

        this.codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(start == 7){
                    if(AppDataBase.getInstance(context).getTableDAO().getTableFromID(Long.parseLong(s.toString())) != null){
                        MainActivity.startActivity(context, Long.parseLong(s.toString()));
                        codeInput.setText("");
                    }else {
                        Toast.makeText(context, "Invalid code", Toast.LENGTH_SHORT).show();
                        codeInput.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ParentProductDB.clear();
    }

    public void onQrCodeScanClick(View view) {
        QrCodeActivity.startActivity(this);
        this.codeInput.setText("");
    }

    private void cacheViews(){
        this.codeInput = findViewById(R.id.editTextCodeInput);

    }

    public void onDebug(View view) {
        MainActivity.startActivity(this,1);
    }
}