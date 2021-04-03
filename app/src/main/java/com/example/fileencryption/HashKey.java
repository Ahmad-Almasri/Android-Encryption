package com.example.fileencryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// SHA-256 HashFunction of a giving key

public class HashKey {

    // hash the key using the sha-256
    public String hash_Kay_SHA256(byte key[]) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digest = messageDigest.digest(key);
        return bytesToHex(digest);
    }
    // convert bytes to HexDecimal
    private String bytesToHex(byte[] digest) {
        StringBuilder hexString = new StringBuilder(2 * digest.length);
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
