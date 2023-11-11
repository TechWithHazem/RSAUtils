package org.example;// MessageDecryptor.java
import java.security.*;
import javax.crypto.Cipher;

import static org.example.KeyUtils.hexToBytes;

public class MessageDecryptor {

    private final PrivateKey privateKey;

    public MessageDecryptor(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String decrypt(String encryptedMessageHex) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] encryptedBytes = hexToBytes(encryptedMessageHex);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Decryption error: " + e.getMessage(), e);
        }
    }

}
