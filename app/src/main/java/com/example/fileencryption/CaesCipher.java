package com.example.fileencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Caesar Cipher Input Text Encryption and Decryption

public class CaesCipher extends AppCompatActivity {
    private EditText text, key;
    private Button btnEnc, btnDec;
    private TextView result;
    private String resultFormat;
    private  char [] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caes_cipher);
        // link XML buttons with JAVA
        text = findViewById(R.id.etTxtCC);
        key = findViewById(R.id.etKeyCC);
        btnEnc = findViewById(R.id.btnEncCC);
        btnDec = findViewById(R.id.btnDecCC);
        result = findViewById(R.id.tvResultCC);

        // Encrypt Button
        btnEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // convert text to uppercase
                data = text.getText().toString().toUpperCase().toCharArray();
                // get the key
                int shift = Integer.parseInt(key.getText().toString());
                // startLetter is A (65) ... lastLetter is (z+1) 91
                int charValue, startLetter = 65, lastLetter = 91; // z+1
                // maximum number for shift is 25
                if(shift>25 || shift<=0){
                    result.setVisibility(View.VISIBLE);
                    result.setText("The key must be <=25 AND >0");
                }else{
                    resultFormat = "";
                    for (char c : data) {
                        charValue = (int) c + shift;
                        if(charValue <= 90) {
                            resultFormat += " " + (char) (charValue);
                        }else{
                            // charValue-lastLetter+startLetter
                            charValue = charValue - lastLetter + startLetter;
                            resultFormat += " " + (char) (charValue);
                        }
                    }
                    result.setVisibility(View.VISIBLE);
                    result.setText("The Encrypted Text:\n" + resultFormat);
                }
            }
        });

        // Decrypt Button
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // convert text to uppercase
                data = text.getText().toString().toUpperCase().toCharArray();
                // get the key
                int shift = Integer.parseInt(key.getText().toString());
                // startLetter is A (65) ... lastLetter is Z (90)
                int charValue, startLetter = 65, lastLetter = 91;
                // maximum number for shift is 25
                if(shift<-25 || shift>=0){
                    result.setVisibility(View.VISIBLE);
                    result.setText("The key must be >= -25 AND <0");
                }else{
                    resultFormat = "";
                    for(char c: data){
                        // check if it is smaller tha 65
                        charValue = (int)c+shift;
                        if(charValue>=65){
                            resultFormat += " " + (char) (charValue);
                        }else{
                            charValue = startLetter - charValue;
                            charValue = lastLetter - charValue;
                            resultFormat += " " + (char) (charValue);
                        }
                    }
                    result.setVisibility(View.VISIBLE);
                    result.setText("The Decrypted Text:\n" + resultFormat);
                }
            }
        });
    }
}