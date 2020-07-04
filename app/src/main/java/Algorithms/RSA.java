package Algorithms;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSA {
    public RSA() {
    }

    public static byte[] encryptRSA(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }
    public static byte[] decryptRSA(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }
}
