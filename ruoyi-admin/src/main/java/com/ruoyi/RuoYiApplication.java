package com.ruoyi;

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
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "                                                                                               hhhhhhh               iiii    \n"+
                "                                                                                               h:::::h              i::::i   \n"+
                "                                                                                               h:::::h               iiii    \n"+
                "                                                                                               h:::::h                       \n"+
                "wwwwwww           wwwww           wwwwwwwaaaaaaaaaaaaa  nnnn  nnnnnnnn         zzzzzzzzzzzzzzzzzh::::h hhhhh       iiiiiii   \n"+
                " w:::::w         w:::::w         w:::::w a::::::::::::a n:::nn::::::::nn       z:::::::::::::::zh::::hh:::::hhh    i:::::i   \n"+
                "  w:::::w       w:::::::w       w:::::w  aaaaaaaaa:::::an::::::::::::::nn      z::::::::::::::z h::::::::::::::hh   i::::i   \n"+
                "   w:::::w     w:::::::::w     w:::::w            a::::ann:::::::::::::::n     zzzzzzzz::::::z  h:::::::hhh::::::h  i::::i   \n"+
                "    w:::::w   w:::::w:::::w   w:::::w      aaaaaaa:::::a  n:::::nnnn:::::n           z::::::z   h::::::h   h::::::h i::::i   \n"+
                "     w:::::w w:::::w w:::::w w:::::w     aa::::::::::::a  n::::n    n::::n          z::::::z    h:::::h     h:::::h i::::i   \n"+
                "      w:::::w:::::w   w:::::w:::::w     a::::aaaa::::::a  n::::n    n::::n         z::::::z     h:::::h     h:::::h i::::i   \n"+
                "       w:::::::::w     w:::::::::w     a::::a    a:::::a  n::::n    n::::n        z::::::z      h:::::h     h:::::h i::::i   \n"+
                "        w:::::::w       w:::::::w      a::::a    a:::::a  n::::n    n::::n       z::::::zzzzzzzzh:::::h     h:::::hi::::::i  \n"+
                "         w:::::w         w:::::w       a:::::aaaa::::::a  n::::n    n::::n      z::::::::::::::zh:::::h     h:::::hi::::::i  \n"+
                "          w:::w           w:::w         a::::::::::aa:::a n::::n    n::::n     z:::::::::::::::zh:::::h     h:::::hi::::::i  \n"+
                "           www             www           aaaaaaaaaa  aaaa nnnnnn    nnnnnn     zzzzzzzzzzzzzzzzzhhhhhhh     hhhhhhhiiiiiiii  \n");
    }
}
