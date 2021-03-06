package com.example.fileencryption;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DecryptActivity extends AppCompatActivity {

    private EditText fileName, key;
    private Button decrypt, listFile;
    private TextView content;
    private String theUsedKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        fileName = findViewById(R.id.etFileNameDec);
        key = findViewById(R.id.etKeyDec);
        content = findViewById(R.id.tvContentDec);
        decrypt = findViewById(R.id.btnDec);
        listFile = findViewById(R.id.btnList);

        // decrypt button
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data input
                readFile(fileName.getText().toString(), key.getText().toString());
            }
        });

        // list button
        listFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File mydir = getFilesDir();
                File lister = mydir.getAbsoluteFile();
                String stringFormat = "";
                for (String list : lister.list())
                {
                    if(!list.contains("-KEY-SHA512") && !list.equals(".txt"))
                        stringFormat+=list+"\n";
                }

                content.setText(stringFormat);
            }
        });
    }


    // this function for reading the data
    private void readFile(String FILE_NAME, String KEY) {

        if (!checkKey(FILE_NAME+"-KEY-SHA512", KEY)) {
            // the key does not match
            content.setText("The key does not match!\n");
            return;
        }


        FileInputStream fileInputStream = null;

        try {
            // open the file
            fileInputStream = openFileInput(FILE_NAME);
            // start reading the file
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String encryptedText;
            // store the content in encryptedText
            while ((encryptedText = bufferedReader.readLine()) != null) {
                stringBuilder.append(encryptedText).append("\n");
            }
            // convert it to string
            encryptedText = stringBuilder.toString();
            //System.out.println(encryptedText.length());
            encryptedText = stringBuilder.deleteCharAt(encryptedText.length() - 1).toString();
            //System.out.println(encryptedText.toCharArray());

            // set Content of the file as a readable text
            Encrypt enc = new Encrypt();
            String data = enc.encryptData(enc.base64Decode(encryptedText), theUsedKey.getBytes());
            content.setText(data);

        } catch (FileNotFoundException e) {
            // in case if the file does not exist
            content.setText("File does not Exist");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the opened file
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // this function to check if the provided key is equal to the hashed one.
    private boolean checkKey(String FILE_NAME, String KEY) {
        FileInputStream fileInputStream = null;

        try {
            // read the file
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String hashedKey;
            while ((hashedKey = bufferedReader.readLine()) != null) {
                stringBuilder.append(hashedKey).append("\n");
            }

            hashedKey = stringBuilder.toString();
            hashedKey = stringBuilder.deleteCharAt(hashedKey.length() - 1).toString();


            String[] splitter = hashedKey.split("%");
            String test = splitter[0]+"%"+splitter[1];
            System.out.println(test);
            // After reading the STORED KEY ... then we need to
            // hash the entered key and check if they are equal.

            // call hash_Kay_SHA256
            HashKey hashKey = new HashKey();
            String testInput = hashKey.getAESKeyFromPassword(KEY.toCharArray(), splitter[0].getBytes());
            String hashedKeyInput = hashKey.hash_Kay_SHA256(testInput.getBytes());

            if(splitter[1].equals(hashedKeyInput)){
                theUsedKey = testInput;
                return true;
            }else{
                return false;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}