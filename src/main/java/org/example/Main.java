package org.example;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                switch (args[0]) {
                    case "generate_keys":
                        RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
                        PublicKey publicKey = keyPairGenerator.getPublicKey();
                        PrivateKey privateKey = keyPairGenerator.getPrivateKey();
                        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
                        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
                        break;
                    case "encrypt":
                        if (args.length == 3) {
                            String publicKeyString = args[1];
                            String message = args[2];
                            PublicKey pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString)));
                            MessageEncryptor encryptor = new MessageEncryptor(pubKey);
                            String encryptedMessage = encryptor.encrypt(message);
                            System.out.println("Encrypted Message: " + encryptedMessage);
                        } else {
                            System.err.println("Invalid arguments for encrypt. Requires public key and message.");
                        }
                        break;
                    case "decrypt":
                        if (args.length == 3) {
                            String privateKeyString = args[1];
                            String encryptedMessage = args[2];
                            PrivateKey privKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString)));
                            MessageDecryptor decryptor = new MessageDecryptor(privKey);
                            String decryptedMessage = decryptor.decrypt(encryptedMessage);
                            System.out.println("Decrypted Message: " + decryptedMessage);
                        } else {
                            System.err.println("Invalid arguments for decrypt. Requires private key and encrypted message.");
                        }
                        break;
                    default:
                        System.err.println("Invalid command. Available commands are: generate_keys, encrypt, decrypt");
                }
            } else {
                System.err.println("No arguments provided. Available commands are: generate_keys, encrypt, decrypt");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
