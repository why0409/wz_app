package com.ruoyi.web.controller.wx.common.uccp.sm4;

import java.math.BigInteger;

public class Util {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] intToBytes(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (0xFF & num >> 0);
        bytes[1] = (byte) (0xFF & num >> 8);
        bytes[2] = (byte) (0xFF & num >> 16);
        bytes[3] = (byte) (0xFF & num >> 24);
        return bytes;
    }

    public static int byteToInt(byte[] bytes) {
        int num = 0;
        int temp = (0xFF & bytes[0]) << 0;
        num |= temp;
        temp = (0xFF & bytes[1]) << 8;
        num |= temp;
        temp = (0xFF & bytes[2]) << 16;
        num |= temp;
        temp = (0xFF & bytes[3]) << 24;
        num |= temp;
        return num;
    }

    public static byte[] longToBytes(long num) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; ++i) {
            bytes[i] = (byte) (int) (0xFF & num >> i * 8);
        }
        return bytes;
    }

    public static byte[] byteConvert32Bytes(BigInteger n) {
        byte[] tmpd;
        if (n == null) {
            return null;
        }

        if (n.toByteArray().length == 33) {
            tmpd = new byte[32];
            System.arraycopy(n.toByteArray(), 1, tmpd, 0, 32);
        } else if (n.toByteArray().length == 32) {
            tmpd = n.toByteArray();
        } else {
            tmpd = new byte[32];
            for (int i = 0; i < 32 - n.toByteArray().length; ++i) {
                tmpd[i] = 0;
            }
            System.arraycopy(n.toByteArray(), 0, tmpd, 32 - n.toByteArray().length, n.toByteArray().length);
        }
        return tmpd;
    }

    public static BigInteger byteConvertInteger(byte[] b) {
        if (b[0] < 0) {
            byte[] temp = new byte[b.length + 1];
            temp[0] = 0;
            System.arraycopy(b, 0, temp, 1, b.length);
            return new BigInteger(temp);
        }
        return new BigInteger(b);
    }

    public static String getHexString(byte[] bytes) {
        return getHexString(bytes, true);
    }

    public static String getHexString(byte[] bytes, boolean upperCase) {
        StringBuilder ret = new StringBuilder();
        for (byte aByte : bytes) {
            ret.append(Integer.toString((aByte & 0xFF) + 256, 16).substring(1));
        }
        return (upperCase ? ret.toString().toUpperCase() : ret.toString());
    }

    public static void printHexString(byte[] bytes) {
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print("0x" + hex.toUpperCase() + ",");
        }
        System.out.println("");
    }

    public static byte[] hexStringToBytes(String hexString) {
        if ((hexString == null) || (hexString.equals(""))) {
            return null;
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; ++i) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
        }
        return d;
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, (toLowerCase) ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];

        int i = 0;
        for (int j = 0; i < l; ++i) {
            out[(j++)] = toDigits[((0xF0 & data[i]) >>> 4)];
            out[(j++)] = toDigits[(0xF & data[i])];
        }
        return out;
    }

    public static String encodeHexString(byte[] data) {
        return encodeHexString(data, true);
    }

    public static String encodeHexString(byte[] data, boolean toLowerCase) {
        return encodeHexString(data, (toLowerCase) ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static String encodeHexString(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    public static byte[] decodeHex(char[] data) {
        int len = data.length;

        if ((len & 0x1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        int i = 0;
        for (int j = 0; i < len; ++i) {
            int f = toDigit(data[j], j) << 4;
            ++j;
            f |= toDigit(data[j], j);
            ++j;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static String stringToAsciiString(String content) {
        StringBuilder result = new StringBuilder();
        int max = content.length();
        for (int i = 0; i < max; ++i) {
            char c = content.charAt(i);
            String b = Integer.toHexString(c);
            result.append(b);
        }
        return result.toString();
    }

    public static String hexStringToString(String hexString, int encodeType) {
        StringBuilder result = new StringBuilder();
        int max = hexString.length() / encodeType;
        for (int i = 0; i < max; ++i) {
            char c = (char) hexStringToAlgorism(hexString.substring(i * encodeType, (i + 1) * encodeType));
            result.append(c);
        }
        return result.toString();
    }

    public static int hexStringToAlgorism(String hex) {
        hex = hex.toUpperCase();
        int max = hex.length();
        int result = 0;
        for (int i = max; i > 0; --i) {
            char c = hex.charAt(i - 1);
            int algorism;
            if ((c >= '0') && (c <= '9'))
                algorism = c - '0';
            else {
                algorism = c - '7';
            }
            result = (int) (result + Math.pow(16.0D, max - i) * algorism);
        }
        return result;
    }

    public static String hexStringToBinary(String hex) {
        hex = hex.toUpperCase();
        StringBuilder result = new StringBuilder();
        int max = hex.length();
        for (int i = 0; i < max; ++i) {
            char c = hex.charAt(i);
            switch (c) {
                case '0':
                    result.append("0000");
                    break;
                case '1':
                    result.append("0001");
                    break;
                case '2':
                    result.append("0010");
                    break;
                case '3':
                    result.append("0011");
                    break;
                case '4':
                    result.append("0100");
                    break;
                case '5':
                    result.append("0101");
                    break;
                case '6':
                    result.append("0110");
                    break;
                case '7':
                    result.append("0111");
                    break;
                case '8':
                    result.append("1000");
                    break;
                case '9':
                    result.append("1001");
                    break;
                case 'A':
                    result.append("1010");
                    break;
                case 'B':
                    result.append("1011");
                    break;
                case 'C':
                    result.append("1100");
                    break;
                case 'D':
                    result.append("1101");
                    break;
                case 'E':
                    result.append("1110");
                    break;
                case 'F':
                    result.append("1111");
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

    public static String asciiStringToString(String content) {
        StringBuilder result = new StringBuilder();
        int length = content.length() / 2;
        for (int i = 0; i < length; ++i) {
            String c = content.substring(i * 2, i * 2 + 2);
            int a = hexStringToAlgorism(c);
            char b = (char) a;
            result.append(b);
        }
        return result.toString();
    }

    public static String algorismToHexString(int algorism, int maxLength) {
        String result = Integer.toHexString(algorism);
        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return patchHexString(result.toUpperCase(), maxLength);
    }

    public static String byteToString(byte[] bytearray) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytearray) {
            char temp = (char) b;
            result.append(temp);
        }
        return result.toString();
    }

    public static int binaryToAlgorism(String binary) {
        int max = binary.length();
        int result = 0;
        for (int i = max; i > 0; --i) {
            char c = binary.charAt(i - 1);
            int algorism = c - '0';
            result = (int) (result + Math.pow(2.0D, max - i) * algorism);
        }
        return result;
    }

    public static String algorismToHEXString(int algorism) {
        String result = Integer.toHexString(algorism);
        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return result.toUpperCase();
    }

    public static String patchHexString(String str, int maxLength) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < maxLength - str.length(); ++i) {
            temp.append("0");
        }
        return temp + str.substring(0, maxLength);
    }

    public static int parseToInt(String s, int defaultInt, int radix) {
        int i;
        try {
            i = Integer.parseInt(s, radix);
        } catch (NumberFormatException ex) {
            i = defaultInt;
        }
        return i;
    }

    public static int parseToInt(String s, int defaultInt) {
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            i = defaultInt;
        }
        return i;
    }

    public static byte[] hexToByte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        int i = 0;
        for (int j = 0; i < hex.length(); ++j) {
            String swap = "" + arr[(i++)] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = (byte) byteint;
            ++i;
        }
        return b;
    }

    public static String byteToHex(byte[] b) {
        if (b == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }

        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte value : b) {
            stmp = Integer.toHexString(value & 0xFF);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] subByte(byte[] input, int startIndex, int length) {
        byte[] bt = new byte[length];
        System.arraycopy(input, startIndex, bt, 0, length);
        return bt;
    }
}
