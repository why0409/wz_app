package com.ruoyi.common.utils;

import com.ruoyi.common.exception.ServiceException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtils {

    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128;

    public static String encrypt(String data, String key, String iv) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(hexStringToByteArray(iv));
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] bytes = data.getBytes("UTF-8");
            return Base64.getEncoder().encodeToString(cipher.doFinal(bytes));
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }

    public static String decrypt(String data, String key, String iv) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(hexStringToByteArray(iv));
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decode = Base64.getDecoder().decode(data);
            return new String(cipher.doFinal(decode), "UTF-8");
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        String data = "Hello, AES encryption!";
        String key = "f7bd2e3dc2805826f0c31c4bb0644a75"; // 通过hexStringToByteArray解析后：16/24/32 bytes
        String iv = "b7ae9e748fa6e4e8aa3298905d221f5b"; // 通过hexStringToByteArray解析后：16字节的初始化向量
        // 加密
        String encrypted = encrypt(data, key, iv);
        System.out.println("Encrypted: " + encrypted);
        // 解密
        String decrypted = decrypt(encrypted, key, iv);
        System.out.println("Decrypted: " + decrypted);
    }
}