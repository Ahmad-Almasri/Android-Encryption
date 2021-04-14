package com.example.fileencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "test.txt";

    private Button btnEncryption, btnDecryption, btnBinary, btnCaesar, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBinary = findViewById(R.id.btnBinary);
        btnCaesar = findViewById(R.id.btnCaesar);
        btnEncryption = findViewById(R.id.btnEncryption);
        btnDecryption = findViewById(R.id.btnDecryption);
        btnDelete = findViewById(R.id.btnDeleteFile);

        btnBinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Binary.class));

            }
        });

        btnCaesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CaesCipher.class));
            }
        });

        btnEncryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EncryptActivity.class));
            }
        });

        btnDecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DecryptActivity.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeleteFile.class));
            }
        });
    }
}
