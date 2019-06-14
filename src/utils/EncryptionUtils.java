package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by barryfan on 6/12/19.
 */
public class EncryptionUtils {

    private static final String AGLSHA256 = "SHA-256";

    public static String encrypPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(AGLSHA256);
            return Base64.getEncoder().encodeToString(messageDigest.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            // ingore
            System.out.println("不支持的算法");
            return null;
        }

    }


}
