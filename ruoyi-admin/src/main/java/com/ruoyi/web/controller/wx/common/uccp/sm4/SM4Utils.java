package com.ruoyi.web.controller.wx.common.uccp.sm4;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SM4加解密工具类
 */
public class SM4Utils {
    public String iv = "UISwD9fW6cFh9SNS";

    public boolean hexString = false;

    private static final String charSet = "UTF-8";
    public static Pattern p = Pattern.compile("\\s*|\t|\r|\n");

    public String encryptData_ECB(String plainText, String secretKey) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 1;
            byte[] keyBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(charSet));
            String cipherText = Base64.getEncoder().encodeToString(encrypted);
            if ((cipherText != null) && (cipherText.trim().length() > 0)) {
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decryptData_ECB(String cipherText, String secretKey) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 0;
            byte[] keyBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.getDecoder().decode(cipherText));
            return new String(decrypted, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encryptData_CBC(String plainText, String secretKey) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 1;
            byte[] keyBytes;
            byte[] ivBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(this.iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = this.iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes(charSet));
            String cipherText = Base64.getEncoder().encodeToString(encrypted);
            if ((cipherText != null) && (cipherText.trim().length() > 0)) {
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decryptData_CBC(String cipherText, String secretKey) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 0;
            byte[] keyBytes;
            byte[] ivBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(this.iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = this.iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.getDecoder().decode(cipherText));
            return new String(decrypted, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
