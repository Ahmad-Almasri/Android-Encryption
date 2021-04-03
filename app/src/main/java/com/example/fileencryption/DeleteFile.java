package com.example.fileencryption;

import androidx.appcompat.app.AppCompatActivity;

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

public class DeleteFile extends AppCompatActivity {
    private EditText fileName, key;
    private Button btnDelete, listFile;
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_file);

        btnDelete = findViewById(R.id.btnDelete);
        listFile = findViewById(R.id.btnList3);
        fileName = findViewById(R.id.delFileName);
        key = findViewById(R.id.delEncKey);
        content = findViewById(R.id.tvContentDec);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the key is correct ...
                String FN = fileName.getText().toString();
                if(checkKey(FN+"-KEY-SHA256.txt", key.getText().toString())){
                    FN+=".txt";
                    File dir = getFilesDir();
                    File file = new File(dir, FN);
                    boolean deleted = file.delete();

                    if(deleted){
                        content.setText("Deleted");
                    }else{
                        content.setText("Not Deleted!");
                    }
                }else{
                    content.setText("The key does not match!\n");
                }

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
                    if(!list.contains("-KEY-SHA256.txt") && !list.equals(".txt"))
                        stringFormat+=list+"\n";
                }

                content.setText(stringFormat);
            }
        });
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

            // After reading the STORED KEY ... then we need to
            // hash the entered key and check if they are equal.

            // call hash_Kay_SHA256
            String hashedKeyInput = new HashKey().hash_Kay_SHA256(KEY.getBytes());

            if(hashedKey.equals(hashedKeyInput)){
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