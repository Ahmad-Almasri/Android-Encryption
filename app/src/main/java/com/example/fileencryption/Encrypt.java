package com.example.fileencryption;


import android.util.Base64;



public class Encrypt {

    public String encryptData(byte data[], byte key[]){

        String output = "";

        for(int i=0, j=0; i<data.length; i++,j++) {
            if(j==key.length) j=0;

            output+= (char) (data[i] ^ key[j]);
        }

        return output;
    }

    public byte[] base64Decode(String s) {
        return Base64.decode(s,Base64.DEFAULT);
    }

    public String base64Encode(byte[] bytes) {
        return new String(Base64.encode(bytes,Base64.DEFAULT));
    }


}
