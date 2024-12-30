package com.ruoyi.common.utils.file;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import cn.hutool.core.codec.Base64Encoder;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
//import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

/**
 * 图片处理工具类
 *
 * @author ruoyi
 */
public class ImageUtils
{
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath)
    {
        InputStream is = getFile(imagePath);
        try
        {
            return IOUtils.toByteArray(is);
        }
        catch (Exception e)
        {
            log.error("图片加载异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath)
    {
        try
        {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        }
        catch (Exception e)
        {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     *
     * @param url 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url)
    {
        InputStream in = null;
        try
        {
            if (url.startsWith("http"))
            {
                // 网络地址
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                in = urlConnection.getInputStream();
            }
            else
            {
                // 本机地址
                String localPath = RuoYiConfig.getProfile();
                String downloadPath = localPath + StringUtils.substringAfter(url, Constants.RESOURCE_PREFIX);
                in = new FileInputStream(downloadPath);
            }
            return IOUtils.toByteArray(in);
        }
        catch (Exception e)
        {
            log.error("获取文件路径异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
    }
    /**
     * base64转图片
     * @param base64Code base64码
     */

    public static void convertBase64ToImage(String base64Code,String path,String fileName) {

        BufferedImage image = null;

        byte[] imageByte = null;

        try {
            imageByte = DatatypeConverter.parseBase64Binary(base64Code);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(new ByteArrayInputStream(imageByte));
            bis.close();
            File outputfile = new File(path+"/"+fileName);
            if(!outputfile.exists()){     //判断文件路径是否存在
                outputfile.mkdirs();              //不存在创建新的文件
            }
            ImageIO.write(image, "jpg", outputfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据图片路径转换成base64
     * @param imageUrl
     * @return
     */
    public static String getBase64(String imageUrl) {
        InputStream in = null;
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        //读取图片字节数组
        try {
            URL url = new URL(imageUrl);
            final byte[] by = new byte[1024];
            // 创建链接获取图片
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            in = conn.getInputStream();
            int len = -1;
            while ((len = in.read(by)) != -1) {
                data.write(by, 0, len);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
        Base64Encoder encoder = new Base64Encoder();
        //返回Base64编码过的字节数组字符串
        String encode = encoder.encode(data.toByteArray());
        /*encode = encode.replaceAll("[\\s*\t\n\r]", "");*/
        return encode;
    }
}
