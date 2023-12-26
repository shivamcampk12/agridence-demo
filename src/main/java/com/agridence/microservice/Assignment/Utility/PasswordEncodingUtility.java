package com.agridence.microservice.Assignment.Utility;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncodingUtility {
    private static String secretKey = "PASSWORDSECERETKEY";
    private static String salt = "PASSWORDSALT";


    public String generateEncodedPassword(String password) {
        String encodedPassword = "";
        try {
            encodedPassword = encodePassword(password);
        } catch (NoSuchAlgorithmException e) {

        } catch (InvalidKeyException e) {

        }
        return encodedPassword;
    }


    private static String encodePassword(String password) throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSHA256";

        String keyWithSalt = secretKey +  salt;

        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, algorithm);

        Mac mac = Mac.getInstance(algorithm);
        mac.init(keySpec);

        byte[] dataBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] result = mac.doFinal(dataBytes);

        return Base64.getEncoder().encodeToString(result);
    }
}

