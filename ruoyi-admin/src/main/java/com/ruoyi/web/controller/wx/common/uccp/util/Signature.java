package com.ruoyi.web.controller.wx.common.uccp.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Signature {

    public static String toSHA256(String str) throws Exception {
        MessageDigest messageDigest;
        String encodeStr;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            throw e;
        }
        return encodeStr;
    }

    // byte转换成16进制
    protected static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
