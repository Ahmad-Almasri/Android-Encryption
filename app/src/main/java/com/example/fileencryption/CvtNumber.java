package com.example.fileencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
* This class for converting A Number to Decimal or Binary System.
*/


public class CvtNumber extends AppCompatActivity {
    private EditText editText;
    private Button b2d, d2b;
    private TextView tvResult;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvt_number);
        // link XML buttons with JAVA
        editText = findViewById(R.id.etNumber);
        b2d = findViewById(R.id.btnB2D);
        d2b = findViewById(R.id.btnD2B);
        tvResult = findViewById(R.id.tvContentDec);


        // Binary to Decimal conversion <BUTTON>
        b2d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the entered data
                data = editText.getText().toString();
                // if in Binary format then convert it
                if(checkDataIsBinary(data.toCharArray()))
                {
                    // convert it to Binary
                    int decimal=Integer.parseInt(data,2);
                    tvResult.setVisibility(View.VISIBLE);
                    tvResult.setText("In Decimal:\n"+ decimal);
                }else{
                    tvResult.setVisibility(View.VISIBLE);
                    tvResult.setText("It is not a binary a number!\n");
                }


            }
        });


        // Decimal to Binary conversion <BUTTON>
        d2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the entered data
                data = editText.getText().toString();
                // convert it to Decimal
                int decimal = Integer.parseInt(data);
                data = Integer.toBinaryString(decimal);
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("In Binary:\n"+ data);
            }
        });
    }


    // check the format of the entered number
    // if it is binary then convert it
    // else return false
    private boolean checkDataIsBinary(char []data) {
        for(char c:data){
            if(c !='1'&&c!='0'){
                return false;
            }
        }

        return true;
    }
}