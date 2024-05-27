package com.ruoyi.common.utils.sign;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Md5加密方法
 * 
 * @author ruoyi
 */
public class Md5Utils
{
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);
    private static final String DES_ALGORITHM = "DES";



    private static byte[] md5(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s)
    {
        try
        {
            return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    /**
     * DES加密操作
     * @param source 要加密的源
     * @param key    约定的密钥
     * @return
     */
    public static String encryptDES(String source,String key){
        //强加密随机数生成器
        SecureRandom random = new SecureRandom();
        try {
            //创建密钥规则
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            //创建密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //按照密钥规则生成密钥
            SecretKey secretKey =  keyFactory.generateSecret(keySpec);
            //加密对象
            Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
            //初始化加密对象需要的属性
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            //开始加密
            byte[] result = cipher.doFinal(source.getBytes());
            //Base64加密
            return  new BASE64Encoder().encode(result) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * md5解密
     * @param cryptograph
     * @param key
     * @return
     */
    public static String decryptDES(String cryptograph,String key){
        //强加密随机生成器
        SecureRandom random  =  new SecureRandom();
        try {
            //定义私钥规则
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            //定义密钥工厂
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            //按照密钥规则生成密钥
            SecretKey secretkey = factory.generateSecret(keySpec);
            //创建加密对象
            Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretkey, random);
            //Base64对
            byte[] result = new BASE64Decoder().decodeBuffer(cryptograph);
            return new String(cipher.doFinal(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
//        String a = "oy6F45WPAzudd0GIvbnRSbA41tKo";
//                System.err.println(encryptDES(a, "12345611"));

        String a = "mCGjVgYiSDDBMhkN+9GEZbtyP9H+nm2MG5fmmE1x/3U=";
        System.err.println(decryptDES(a, "6y8SwEs8Fu8YXwvq"));
    }
}
