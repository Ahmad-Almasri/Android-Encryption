package com.example.fileencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Binary extends AppCompatActivity {

    private Button numberBtn, txtBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary);

        numberBtn = findViewById(R.id.numberBtn);
        txtBtn = findViewById(R.id.txtBtn);

        numberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Binary.this, CvtNumber.class));
            }
        });

        txtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Binary.this, CvtTxtToBin.class));
            }
        });
    }
}