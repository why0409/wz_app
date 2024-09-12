package com.ruoyi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
public class RuoYiApplication {

    private static String serverPort;

    @Value("${server.port}")
    public void setServerPort(String serverPort){
        RuoYiApplication.serverPort = serverPort;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("API文档地址:http://localhost:"+serverPort+"/doc.html");
    }
}
