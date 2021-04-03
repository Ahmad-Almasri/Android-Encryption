package com.example.fileencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Convert input text to numbers in Decimal or Binary.

public class CvtTxtToBin extends AppCompatActivity {
    private EditText editText;
    private Button t2b, t2d;
    private TextView result;
    private String resultFormat;
    private char[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvt_txt_to_bin);

        editText = findViewById(R.id.etCvtTxt);
        t2b = findViewById(R.id.btnCvtBin);
        t2d = findViewById(R.id.btnCvtDec);
        result = findViewById(R.id.tvContentDec);

        // text to binary
        t2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = editText.getText().toString().toCharArray();
                resultFormat = "";
                for(char ch : data) {
			        resultFormat = resultFormat + " " + Integer.toBinaryString((int)ch);
		        }

                result.setVisibility(View.VISIBLE);
                result.setText("In Binary:\n"+resultFormat);
            }
        });

        // text to Decimal
        t2d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = editText.getText().toString().toCharArray();
                resultFormat = "";
                for(char ch : data) {
                    resultFormat = resultFormat + " " + (int)ch;
                }
                result.setVisibility(View.VISIBLE);
                result.setText("In Decimal:\n"+resultFormat);
            }
        });
    }
}
