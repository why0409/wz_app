package com.ruoyi.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密解密
 *
 * @author ruoyi
 **/
public class RsaUtils
{
    //公钥
    private static String publicKeyStr ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCis9oHAw03tML4S+FF1BXt88JsRsjfaqaLBUNOE1K+2vgGQt08VRWAxMJzN0UVWyVY3y23sD3CJHq3jsoCpOdGN6vhV8I7IL7J9ZdbUME/EgYXaxnGFogcT5dT6vokMID1LgC4hhBiocatfkeEUA2s08mtZPH4Oh2u6NlTNcNsqwIDAQAB\n";
    //私钥
    private static String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKKz2gcDDTe0wvhL4UXUFe3zwmxGyN9qposFQ04TUr7a+AZC3TxVFYDEwnM3RRVbJVjfLbewPcIkereOygKk50Y3q+FXwjsgvsn1l1tQwT8SBhdrGcYWiBxPl1Pq+iQwgPUuALiGEGKhxq1+R4RQDazTya1k8fg6Ha7o2VM1w2yrAgMBAAECgYAFgF9W7Vt9Dreo/afYXJuBOETStHIGW+zVto/YC/84RN8uFwS0DYx0ljKdArlrR3Gz15gMJgFwlxsvKbza6giH4TXXVwnYExNXXjUNXqIsFaMH2AjVbc93SLSERYnQyIKFnqtO8iSinevSH8E5a/LlOEI+j/HkPyMSiRq+3QTrwQJBAPlHIIen9JPEn11ATohdu/49an1zJpY1PTXFR0zCFLw4Mhe9tSlvR2RXPXl/swRPoeWuHUYojLwcPzDJzCdt73UCQQCnFw7OHhMm7AVy3KTZE7fHLbP3vRi5+2jI+iN8Prk8c32ioY5IzsQSLdClM8/GKR0MqvLBZDL/eHahRSJYwYefAkB8TGo9GpPLRyUME6/VMaA3IxrBmTSpe7yc3lUyQiiivgbEbdUy5wkbUKEXv4RdZvPcmliAjmG2vwMHaeiB1MCtAkAe3hCbHU02dIWxKXe+rnjV7+h+TM2jlgFOl380tbWsqD0+bRAG/UOVhCc2TbMFjZTTQUVlWqhdL5jixA3LTb1LAkEA0SOXcgtG9MEhGoLt26WqBH3eArO1aTBjWtKIRD1+AfPMMk3A6DGeMQsL44CJPi2zTc/uMVbhPoATTSyZoYYBDA==\n";


    /**
     * 私钥解密
     *
     * @param text 待解密的文本
     * @return 解密后的文本
     */
//    public static String decryptByPrivateKey(String text) throws Exception
//    {
//        return decryptByPrivateKey(privateKey, text);
//    }

    /**
     * 公钥解密
     *
     * @param text 待解密的信息
     * @return 解密后的文本
     */
    public static String decryptByPublicKey( String text) throws Exception
    {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param text 待加密的信息
     * @return 加密后的文本
     */
    public static String encryptByPrivateKey( String text) throws Exception
    {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     *
     * @param text 待解密的文本
     * @return 解密后的文本
     */
    public static   String decryptByPrivateKey(String text) throws Exception
    {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param text 待加密的文本
     * @return 加密后的文本
     */
    public static  String encryptByPublicKey( String text) throws Exception
    {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 构建RSA密钥对
     *
     * @return 生成后的公私钥信息
     */
    public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        System.out.println(publicKeyString);
        System.out.println(privateKeyString);
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }

    /**
     * RSA密钥对对象
     */
    public static  class RsaKeyPair
    {
        private final String publicKey;
        private final String privateKey;

        public RsaKeyPair(String publicKey, String privateKey)
        {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey()
        {
            return publicKey;
        }

        public String getPrivateKey()
        {
            return privateKey;
        }
    }


    public static void main(String[] args) throws Exception {
        String s = "hello world";        //公钥加密
        String key = RsaUtils.encryptByPublicKey(s);
        //私钥解密
        System.out.println("解密后:"+RsaUtils.decryptByPrivateKey(key));

    }

}
