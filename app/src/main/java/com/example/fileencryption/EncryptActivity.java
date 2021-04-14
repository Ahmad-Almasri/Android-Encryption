package com.example.fileencryption;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class EncryptActivity extends AppCompatActivity {

    private EditText fileName, text, key;
    private Button encrypt, listFile;
    private TextView content;
    private String theUsedKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        fileName = findViewById(R.id.etFileName);
        text = findViewById(R.id.etText);
        key = findViewById(R.id.etEncryptionKey);
        encrypt = findViewById(R.id.btnEncrypt);
        listFile = findViewById(R.id.btnList2);
        content = findViewById(R.id.tvContentDec);

        /*
        * The text is going to be encrypted using bit wise XOR arithmetic
        * Then the encrypted text is going to be stored on the device, and fileName (is provided by the user)
        * Finally, the key is going to be <Hashed and stored> on the device as well.
        * */

        encrypt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                // get data from the input field
                String userFileName = fileName.getText().toString();
                String userText = text.getText().toString();
                String userKey = key.getText().toString();

                try {
                    fileKeyInit(userFileName, userKey);
                    fileContentInit(userFileName, userText, theUsedKey);
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                    e.printStackTrace();
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
                    if(!list.contains("-KEY-SHA512") && !list.equals(".txt"))
                        stringFormat+=list+"\n";
                }

                content.setText(stringFormat);
            }
        });


    }
    // Initialize a file to store the encrypted content
    public void fileContentInit(String FILE_NAME, String content, String KEY) throws UnsupportedEncodingException {
        FileOutputStream fileOutputStream = null;
        // call encryptData function
        byte[] EncryptedData = new Encrypt().encryptData(content.getBytes(), KEY.getBytes()).getBytes();
        try {
            // Open a file on a private mode
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            //write the content in Bytes
            fileOutputStream.write(new Encrypt().base64Encode(EncryptedData).getBytes());
            // clear the input fields
            fileName.getText().clear();
            text.getText().clear();
            key.getText().clear();
            // Toast message includes the file path
            Toast.makeText(getApplicationContext(), "File Saved To" + getFilesDir() +"/"+ FILE_NAME , Toast.LENGTH_LONG).show();
            System.out.println("File Saved To" + getFilesDir() +"/"+ FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Initialize a file to store the hashed key
    public void fileKeyInit(String FILE_NAME, String KEY) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // make a separate file to store the hashed key
        // store the hash in a file call FILE_NAME+'-KEY-SHA256'
        FILE_NAME = FILE_NAME + "-KEY-SHA512";
        FileOutputStream fileOutputStream = null;
        // call hash_Kay_SHA512
        HashKey hashKey = new HashKey();

        String salt = hashKey.bytesToHex(hashKey.getRandomNonce());
        String PKDF2 = hashKey.getAESKeyFromPassword(KEY.toCharArray(),salt.getBytes());
        theUsedKey = PKDF2;
        String hashedKey = hashKey.hash_Kay_SHA256(PKDF2.getBytes());

        try {
            // create a file
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            // write the content into the file
            String test = salt+"%"+hashedKey;
            System.out.println(test);
            fileOutputStream.write(test.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}