package com.example.fivemealsmobileproject.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fivemealsmobileproject.MainActivity;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;

public class CodeActivity extends AppCompatActivity {

    private EditText codeInput;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
        context.startActivity(intent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Acabar o design
        setContentView(R.layout.activity_code_input);
        Toast.makeText(this, "QrCode", Toast.LENGTH_SHORT).show();
        cacheViews();
        Context context = this;
       

        this.codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(start == 7){
                    if(AppDataBase.getInstance(context).getTableDAO().getTableFromID(Long.parseLong(s.toString())) != null){
                        MainActivity.startActivity(context, Long.parseLong(s.toString()));
                    }else {
                        Toast.makeText(context, "Invalid code", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void onQrCodeScanClick(View view) {
        QrCodeActivity.startActivity(this);
    }

    private void cacheViews(){
        this.codeInput = findViewById(R.id.editTextCodeInput);

    }
}