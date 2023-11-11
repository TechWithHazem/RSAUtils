package org.example;// MessageEncryptor.java
import java.security.*;
import javax.crypto.Cipher;

import static org.example.RSAKeyPairGenerator.bytesToHex;

public class MessageEncryptor {

    private final PublicKey publicKey;

    public MessageEncryptor(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String encrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return bytesToHex(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error: " + e.getMessage(), e);
        }
    }
}
