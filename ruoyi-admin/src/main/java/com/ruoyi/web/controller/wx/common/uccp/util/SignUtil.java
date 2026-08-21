package com.ruoyi.web.controller.wx.common.uccp.util;

import com.ruoyi.common.utils.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * UCCP签名及加解密工具类
 */
public class SignUtil {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final String ENCODING_ALGORITHM = "MD5";

    private static final String CHARACTER_ENCODING = "UTF-8";

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return buf.toString();
    }

    /**
     * md5签名
     *
     * @param password 待签名内容
     * @return 签名结果
     */
    public static String md5Encode(final String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCODING_ALGORITHM);
            if (StringUtils.isNotEmpty(CHARACTER_ENCODING)) {
                messageDigest.update(password.getBytes(CHARACTER_ENCODING));
            } else {
                messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            }
            final byte[] digest = messageDigest.digest();
            return getFormattedText(digest);
        } catch (final NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取授权码请求签名
     */
    public static String signAuthCode(String appCode, String appSecret, String time) {
        TreeMap<String, String> signParams = new TreeMap<>();
        signParams.put("time", time);
        signParams.put("appCode", appCode);
        signParams.put("appKey", appSecret);
        String signData = produceData(signParams);
        return md5Encode(signData + appSecret);
    }

    /**
     * 获取用户信息请求签名
     */
    public static String signUserInfo(String appCode, String appSecret, String token, String time, String authCode) {
        TreeMap<String, String> signParams = new TreeMap<>();
        signParams.put("authCode", authCode);
        signParams.put("time", time);
        signParams.put("appCode", appCode);
        signParams.put("token", token);
        String signData = produceData(signParams);
        return md5Encode(signData + appSecret);
    }

    /**
     * AES加密
     */
    public static String encryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
        byte[] enCodeFormat = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        byte[] initParam = key.getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(byteContent);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * AES解密
     */
    public static String decryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, IOException {
        byte[] encryptedBytes = Base64.getDecoder().decode(content);
        byte[] enCodeFormat = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
        byte[] initParam = key.getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] result = cipher.doFinal(encryptedBytes);
        return new String(result, StandardCharsets.UTF_8);
    }

    /**
     * 生成待签名数据
     */
    public static String produceData(Map<String, String> params) {
        Collection<String> keyset = params.keySet();
        List<String> list = new ArrayList<>(keyset);
        Collections.sort(list);

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            data.append(list.get(i)).append("=").append(params.get(list.get(i)));
            if (i != list.size() - 1) {
                data.append("&");
            }
        }
        String p = null;
        try {
            p = URLEncoder.encode(data.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 获得指定长度的随机字符串
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        if (length < 1) {
            length = 1;
        }
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
