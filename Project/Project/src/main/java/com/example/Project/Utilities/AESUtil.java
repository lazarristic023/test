package com.example.Project.Utilities;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class AESUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ALGORITHM = "AES";
    //rezim za siforvanje
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;

    private static SecretKey secretKey;
    private static IvParameterSpec iv;

    static {
        try {
            // Učitajte keystore iz resources foldera
            String keystorePath = "/mykeystore.jceks";
            char[] keystorePassword = "password".toCharArray();
            String keyAlias = "mykey";
            char[] keyPassword = "password".toCharArray();

            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            try (InputStream is = AESUtil.class.getResourceAsStream(keystorePath)) {
                keyStore.load(is, keystorePassword);
            }

            // Dobijanje tajnog ključa iz keystore-a
            secretKey = (SecretKey) keyStore.getKey(keyAlias, keyPassword);

            // Generisanje inicijalizacionog vektora (IV)
            iv = generateIv();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    private static IvParameterSpec generateIv() {
        byte[] ivBytes = new byte[IV_SIZE];
        // Generisanje slučajnog IV-a
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }
}
