package com.example.fileencryption;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Encrypt {
    /*
		String txt = "University Of calgary == D2L == Worksheet 03";
		String key = "Calgary";
		Cipher cipher;
		byte[] txtEnc, keyEnc, output;
		try {

			txtEnc = txt.getBytes(UNICODE_FORMAT);
			keyEnc = txt.getBytes(UNICODE_FORMAT);
			output = new byte[txtEnc.length];

			for(int i=0, j=0; i<txtEnc.length; i++,j++) {
				if(j==key.length()) j=0;
				output[i] = (byte) (txtEnc[i] ^ keyEnc[j]);
			}
			System.out.println("Encrypted data:");
			display(output);

			for(int i=0, j=0; i<txtEnc.length; i++,j++) {
				if(j==key.length()) j=0;
				output[i] = (byte) (output[i] ^ keyEnc[j]);
			}
			System.out.println("Decrypted data:");
			display(output);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void display(byte[] output) {
		for(int i = 0; i < output.length; i++)
			System.out.printf("%c", output[i]);
		System.out.println();
	}
*/
    public String encryptData(char data[], char key[]) throws UnsupportedEncodingException {

        String output = "";

        for(int i=0, j=0; i<data.length; i++,j++) {
            if(j==key.length) j=0;

            output+= (char) (data[i] ^ key[j]);
        }

        return output;
    }
}
